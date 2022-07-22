package com.example.realestaterent.service;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.entity.PropertyEntity;
import com.example.realestaterent.exception.PropertyNotFoundException;
import com.example.realestaterent.model.Property;
import com.example.realestaterent.repository.AddressRepo;
import com.example.realestaterent.repository.OwnerRepo;
import com.example.realestaterent.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private AddressRepo addressRepo;

    public PropertyEntity getOne(Long id) throws Exception {
        PropertyEntity property = propertyRepo.findById(id).get();
        if (property == null) {
            throw new PropertyNotFoundException();
        }
        return property;
    }

    public List<PropertyEntity> getAll() throws Exception {
        List<PropertyEntity> property = (List<PropertyEntity>) propertyRepo.findAll();
        if (property.isEmpty()) {
            throw new PropertyNotFoundException();
        }
        return property;
    }

    public PropertyEntity create(PropertyEntity property) {
        propertyRepo.save(property);
        return property;
    }

    public PropertyEntity update(PropertyEntity property) {
        create(property);
        return property;
    }

    public Long deleteOne(Long id) {
        propertyRepo.deleteById(id);
        return id;
    }

    public PropertyEntity toPropertyEntity(Property property){
        OwnerEntity ownerEntity = ownerRepo.findById(property.getOwner_idowner()).get();
        AddressEntity addressEntity = addressRepo.findById(property.getAddress_idaddress()).get();
        PropertyEntity propertyEntity = new PropertyEntity(property.getIdproperty(), property.getPropertyType(), property.getRentType(),
                property.getRoomsAmount(), property.getSquare(), property.getFloor(), property.getPriceDollar(),
                property.getDescription(), property.getPhoto(), ownerEntity, addressEntity);
        return propertyEntity;
    }

}
