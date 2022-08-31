package com.example.realestaterent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "address")
public class Address {
    @ApiModelProperty(value = "country", required = true)
    private String country;
    @ApiModelProperty(value = "region")
    private String region;
    @ApiModelProperty(value = "city", required = true)
    private String city;
    @ApiModelProperty(value = "street", required = true)
    private String street;
    @ApiModelProperty(value = "houseNumber", required = true)
    private String houseNumber;
    @ApiModelProperty(value = "propertyList")
    private List<Property> propertyList = new ArrayList<>();

    public Address() {
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

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
