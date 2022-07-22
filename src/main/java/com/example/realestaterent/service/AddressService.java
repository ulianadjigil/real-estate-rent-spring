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

    public AddressEntity getOne(Long id) throws Exception {
        AddressEntity address = addressRepo.findById(id).get();
        if (address == null) {
            throw new AddressNotFoundException();
        }
        return address;
    }

    public List<AddressEntity> getAll() throws Exception {
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

    public AddressEntity update(AddressEntity address) {
        create(address);
        return address;
    }

    public Long deleteOne(Long id) {
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
