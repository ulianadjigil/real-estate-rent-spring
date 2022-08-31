package com.example.realestaterent.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@ApiModel(value = "owner")
public class OwnerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "idowner")
    private Long idowner;
    @Column(nullable = false)
    @ApiModelProperty(value = "name", required = true)
    private String name;
    @Column(nullable = false)
    @ApiModelProperty(value = "surname", required = true)
    private String surname;
    @Column(nullable = false)
    @ApiModelProperty(value = "patronymic", required = true)
    private String patronymic;
    @Column(unique = true, nullable = false)
    @ApiModelProperty(value = "email", required = true)
    private String email;
    @Column(unique = true, nullable = false)
    @ApiModelProperty(value = "phone", required = true)
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @ApiModelProperty(value = "propertyEntityList")
    private List<PropertyEntity> propertyEntityList = new ArrayList<>();

    public OwnerEntity() {
    }

    public OwnerEntity(String name, String surname, String patronymic, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
    }

    public Long getIdOwner() {
        return idowner;
    }

    public void setIdOwner(Long idowner) {
        this.idowner = idowner;
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

    public List<PropertyEntity> getPropertyEntityList() {
        return propertyEntityList;
    }

    public void setPropertyEntityList(List<PropertyEntity> propertyEntityList) {
        this.propertyEntityList = propertyEntityList;
    }
}
