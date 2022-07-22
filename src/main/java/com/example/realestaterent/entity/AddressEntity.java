package com.example.realestaterent.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idaddress;
    private String country;
    private String region;
    private String city;
    private String street;
    private String houseNumber;

    @OneToMany(mappedBy = "address", cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private Set<PropertyEntity> propertyEntitySet = new TreeSet<>();

    public AddressEntity() {
    }

    public AddressEntity(Long idaddress, String country, String region, String city, String street, String houseNumber) {
        this.idaddress = idaddress;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Long idaddress) {
        this.idaddress = idaddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Set<PropertyEntity> getPropertyEntitySet() {
        return propertyEntitySet;
    }

    public void setPropertyEntitySet(Set<PropertyEntity> propertyEntitySet) {
        this.propertyEntitySet = propertyEntitySet;
    }
}
