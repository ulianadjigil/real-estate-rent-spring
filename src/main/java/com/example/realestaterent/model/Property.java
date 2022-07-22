package com.example.realestaterent.model;

public class Property {
    private Long idproperty;
    private String propertyType;

    private String rentType;

    private String roomsAmount;

    private String square;

    private String floor;

    private String priceDollar;

    private String description;

    private String photo;

    private Long owner_idowner;

    private Long address_idaddress;

    public Property() {
    }

    public Long getIdproperty() {
        return idproperty;
    }

    public void setIdproperty(Long idproperty) {
        this.idproperty = idproperty;
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

    public Long getOwner_idowner() {
        return owner_idowner;
    }

    public void setOwner_idowner(Long owner_idowner) {
        this.owner_idowner = owner_idowner;
    }

    public Long getAddress_idaddress() {
        return address_idaddress;
    }

    public void setAddress_idaddress(Long address_idaddress) {
        this.address_idaddress = address_idaddress;
    }
}
