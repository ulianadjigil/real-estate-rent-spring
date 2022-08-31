package com.example.realestaterent.service;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.exception.AddressNotFoundException;
import com.example.realestaterent.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public AddressEntity getOne(Long id) throws AddressNotFoundException {
        AddressEntity address = addressRepo.findById(id).orElse(null);
        if (address == null) {
            throw new AddressNotFoundException();
        }
        return address;
    }

    public List<AddressEntity> getAll() throws AddressNotFoundException {
        List<AddressEntity> address = (List<AddressEntity>) addressRepo.findAll();
        if (address.isEmpty()) {
            throw new AddressNotFoundException();
        }
        return address;
    }

    public AddressEntity create(AddressEntity address) {
        addressRepo.save(address);
        return address;
    }

    public AddressEntity update(Long id, AddressEntity addressData) throws AddressNotFoundException{
        AddressEntity address = addressRepo.findById(id).orElse(null);
        if (address == null) {
            throw new AddressNotFoundException();
        }
        addressData.setIdAddress(id);
        addressRepo.save(addressData);
        return address;
    }

    public Long deleteOne(Long id) throws AddressNotFoundException{
        AddressEntity address = addressRepo.findById(id).orElse(null);
        if (address == null) {
            throw new AddressNotFoundException();
        }
        addressRepo.deleteById(id);
        return id;
    }

    public Boolean exists(AddressEntity address) {
        if(addressRepo.findAddress(address.getCountry(), address.getRegion(), address.getCity(),
                address.getStreet(), address.getHouseNumber()).isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}