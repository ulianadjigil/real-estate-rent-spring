package com.example.realestaterent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "property")
public class Property {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "propertyType", required = true)
    private String propertyType;
    @ApiModelProperty(value = "rentType", required = true)
    private String rentType;
    @ApiModelProperty(value = "roomsAmount", required = true)
    private String roomsAmount;
    @ApiModelProperty(value = " square", required = true)
    private String square;
    @ApiModelProperty(value = "floor")
    private String floor;
    @ApiModelProperty(value = "priceDollar", required = true)
    private String priceDollar;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "photo")
    private String photo;
    @ApiModelProperty(value = "ownerId")
    private Long ownerId;
    @ApiModelProperty(value = "addressId")
    private Long addressId;

    public Property(Long id, String propertyType, String rentType, String roomsAmount, String square, String floor, String priceDollar, String description, String photo, Long ownerId, Long addressId) {
        this.id = id;
        this.propertyType = propertyType;
        this.rentType = rentType;
        this.roomsAmount = roomsAmount;
        this.square = square;
        this.floor = floor;
        this.priceDollar = priceDollar;
        this.description = description;
        this.photo = photo;
        this.ownerId = ownerId;
        this.addressId = addressId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(String roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPriceDollar() {
        return priceDollar;
    }

    public void setPriceDollar(String priceDollar) {
        this.priceDollar = priceDollar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", propertyType='" + propertyType + '\'' +
                ", rentType='" + rentType + '\'' +
                ", roomsAmount='" + roomsAmount + '\'' +
                ", square='" + square + '\'' +
                ", floor='" + floor + '\'' +
                ", priceDollar='" + priceDollar + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", ownerId=" + ownerId +
                ", addressId=" + addressId +
                '}';
    }
}
