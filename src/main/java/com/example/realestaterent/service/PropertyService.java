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

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private AddressRepo addressRepo;

    public Property getOne(Long id) throws PropertyNotFoundException {
        PropertyEntity propertyEntity = propertyRepo.findById(id).orElse(null);
        if (propertyEntity == null) {
            throw new PropertyNotFoundException();
        }
        return toProperty(propertyEntity);
    }

    public List<Property> getAll() throws PropertyNotFoundException {
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepo.findAll();
        if (propertyEntityList.isEmpty()) {
            throw new PropertyNotFoundException();
        }
        List<Property> properties = new ArrayList<>();
        for(PropertyEntity propertyEntity : propertyEntityList){
            Property property = toProperty(propertyEntity);
            properties.add(property);
        }
        return properties;
    }

    public Property create(Property property) {
        PropertyEntity propertyEntity = toPropertyEntity(property);
        propertyRepo.save(propertyEntity);
        return property;
    }

    public Property update(Long id, Property property) throws PropertyNotFoundException{
        PropertyEntity propertyData = toPropertyEntity(property);
        PropertyEntity propertyEntity = propertyRepo.findById(id).orElse(null);
        if (propertyEntity == null) {
            throw new PropertyNotFoundException();
        }
        propertyData.setIdProperty(id);
        propertyRepo.save(propertyData);
        return property;
    }

    public Long deleteOne(Long id) throws PropertyNotFoundException{
        PropertyEntity property = propertyRepo.findById(id).orElse(null);
        if (property == null) {
            throw new PropertyNotFoundException();
        }
        propertyRepo.deleteById(id);
        return id;
    }

    public PropertyEntity toPropertyEntity(Property property){
        OwnerEntity ownerEntity = ownerRepo.findById(property.getOwnerId()).orElse(null);
        AddressEntity addressEntity = addressRepo.findById(property.getAddressId()).orElse(null);
        PropertyEntity propertyEntity = new PropertyEntity(property.getPropertyType(), property.getRentType(),
                property.getRoomsAmount(), property.getSquare(), property.getFloor(), property.getPriceDollar(),
                property.getDescription(), property.getPhoto(), ownerEntity, addressEntity);
        return propertyEntity;
    }

    public Property toProperty(PropertyEntity propertyEntity){
        Long ownerId = null;
        if(propertyEntity.getOwner() != null){
            ownerId = propertyEntity.getOwner().getIdOwner();
        }
        Long addressId = null;
        if(propertyEntity.getAddress() != null){
            addressId = propertyEntity.getAddress().getIdAddress();
        }
        Property property = new Property(propertyEntity.getIdProperty(), propertyEntity.getPropertyType(),
                propertyEntity.getRentType(), propertyEntity.getRoomsAmount(), propertyEntity.getSquare(),
                propertyEntity.getFloor(), propertyEntity.getPriceDollar(), propertyEntity.getDescription(),
                propertyEntity.getPhoto(), ownerId,
                addressId);
        return property;
    }

}
