package com.example.realestaterent.service;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.entity.PropertyEntity;
import com.example.realestaterent.exception.OwnerNotFoundException;
import com.example.realestaterent.model.Owner;
import com.example.realestaterent.model.Property;
import com.example.realestaterent.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;

    public OwnerEntity getOne(Long id) throws OwnerNotFoundException {
        OwnerEntity owner = ownerRepo.findById(id).orElse(null);
        if (owner == null) {
            throw new OwnerNotFoundException();
        }
        return owner;
    }

    public List<OwnerEntity> getAll() throws OwnerNotFoundException{
        List<OwnerEntity> owners = (List<OwnerEntity>) ownerRepo.findAll();
        if (owners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        return owners;
    }


    public List<OwnerEntity> getByNameSurname(String name, String surname) throws OwnerNotFoundException{
        List<Long> ownersId = ownerRepo.findByNameSurname(name, surname);
        if (ownersId.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        List<OwnerEntity> owners = new ArrayList<>();
        for(Long id: ownersId){
            ownerRepo.findById(id).ifPresent(owners::add);
        }
        if (owners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        return owners;
    }

    public Page<OwnerEntity> getPaginated(Integer pageNo, Integer pageSize, String sortBy) throws OwnerNotFoundException {
        Page<OwnerEntity> pagedOwners = ownerRepo.findAll(PageRequest.of(pageNo, pageSize).withSort(Sort.by(sortBy)));
        if (pagedOwners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        return pagedOwners;
    }

    public OwnerEntity create(OwnerEntity owner) {
        ownerRepo.save(owner);
        return owner;
    }

    public OwnerEntity update(Long id, OwnerEntity ownerData) throws OwnerNotFoundException{
        OwnerEntity owner = ownerRepo.findById(id).orElse(null);
        if (owner == null) {
            throw new OwnerNotFoundException();
        }
        ownerData.setIdOwner(id);
        ownerRepo.save(ownerData);
        return owner;
    }

    public Long deleteOne(Long id) throws OwnerNotFoundException{
        List<OwnerEntity> owners = (List<OwnerEntity>) ownerRepo.findAll();
        if (owners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        ownerRepo.deleteById(id);
        return id;
    }

    public Boolean exists(OwnerEntity owner) {
        if((ownerRepo.findOwnerByEmail(owner.getEmail()) == null) &&
                (ownerRepo.findOwnerByPhone(owner.getPhone()) == null)){
            return false;
        }else {
            return true;
        }

    }
}
