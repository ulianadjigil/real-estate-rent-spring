package com.example.realestaterent.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
@ApiModel(value = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "idaddress")
    private Long idaddress;
    @Column(nullable = false)
    @ApiModelProperty(value = " country", required = true)
    private String country;
    @ApiModelProperty(value = "region")
    private String region;
    @Column(nullable = false)
    @ApiModelProperty(value = "city", required = true)
    private String city;
    @Column(nullable = false)
    @ApiModelProperty(value = "street", required = true)
    private String street;
    @Column(nullable = false)
    @ApiModelProperty(value = "houseNumber", required = true)
    private String houseNumber;

    @OneToMany(mappedBy = "address", cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @ApiModelProperty(value = "propertyEntityList")
    private List<PropertyEntity> propertyEntityList = new ArrayList<>();

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

    public Long getIdAddress() {
        return idaddress;
    }

    public void setIdAddress(Long idaddress) {
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

    public List<PropertyEntity> getPropertyEntityList() {
        return propertyEntityList;
    }

    public void setPropertyEntityList(List<PropertyEntity> propertyEntityList) {
        this.propertyEntityList = propertyEntityList;
    }
}
