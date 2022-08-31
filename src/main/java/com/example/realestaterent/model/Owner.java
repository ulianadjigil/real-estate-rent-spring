package com.example.realestaterent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "ownerEnt")
public class Owner {
    @ApiModelProperty(value = "name", required = true)
    private String name;
    @ApiModelProperty(value = "surname", required = true)
    private String surname;
    @ApiModelProperty(value = "patronymic", required = true)
    private String patronymic;
    @ApiModelProperty(value = "email", required = true)
    private String email;
    @ApiModelProperty(value = "phone", required = true)
    private String phone;
    @ApiModelProperty(value = "propertyList")
    private List<Property> propertyList = new ArrayList<>();

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
