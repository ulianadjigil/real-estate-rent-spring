package com.example.realestaterent.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idproperty;
    @Column(nullable = false)
    private String propertyType;
    @Column(nullable = false)
    private String rentType;
    @Column(nullable = false)
    private String roomsAmount;
    @Column(nullable = false)
    private String square;
    private String floor;
    @Column(nullable = false)
    private String priceDollar;
    private String description;
    private String photo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private OwnerEntity owner;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private AddressEntity address;

    public PropertyEntity() {
    }

    public PropertyEntity(String propertyType, String rentType, String roomsAmount, String square, String floor, String priceDollar, String description, String photo, OwnerEntity owner, AddressEntity address) {
        this.propertyType = propertyType;
        this.rentType = rentType;
        this.roomsAmount = roomsAmount;
        this.square = square;
        this.floor = floor;
        this.priceDollar = priceDollar;
        this.description = description;
        this.photo = photo;
        this.owner = owner;
        this.address = address;
    }

    public PropertyEntity(Long idproperty, String propertyType, String rentType, String roomsAmount, String square, String floor, String priceDollar, String description, String photo, OwnerEntity owner, AddressEntity address) {
        this.idproperty = idproperty;
        this.propertyType = propertyType;
        this.rentType = rentType;
        this.roomsAmount = roomsAmount;
        this.square = square;
        this.floor = floor;
        this.priceDollar = priceDollar;
        this.description = description;
        this.photo = photo;
        this.owner = owner;
        this.address = address;
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
