package com.example.realestaterent.service;

import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.exception.OwnerNotFoundException;
import com.example.realestaterent.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;

    public OwnerEntity getOne(Long id) throws Exception {
        OwnerEntity owner = ownerRepo.findById(id).get();
        if (owner == null) {
            throw new OwnerNotFoundException();
        }
        return owner;
    }

    public List<OwnerEntity> getAll() throws Exception{
        List<OwnerEntity> owners = (List<OwnerEntity>) ownerRepo.findAll();
        if (owners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        return owners;
    }

    public Page<OwnerEntity> getPaginated(Integer pageNo, Integer pageSize, String sortBy) throws Exception {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<OwnerEntity> pagedOwners = ownerRepo.findAll(paging);
        if (pagedOwners.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        return pagedOwners;
    }

    public OwnerEntity create(OwnerEntity owner) {
        ownerRepo.save(owner);
        return owner;
    }

    public OwnerEntity update(OwnerEntity owner) {
        create(owner);
        return owner;
    }

    public Long deleteOne(Long id) {
        ownerRepo.deleteById(id);
        return id;
    }

    public Boolean exists(OwnerEntity owner) {
        if((ownerRepo.findOwnerByEmail(owner.getEmail()) == null) &&
                (ownerRepo.findOwnerByPhone(owner.getEmail()) == null)){
            return false;
        }else {
            return true;
        }

    }
}
