package com.example.realestaterent.entity;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idowner;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String patronymic;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private Set<PropertyEntity> propertyEntitySet = new TreeSet<>();

    public OwnerEntity() {
    }

    public OwnerEntity(Long idowner, String name, String surname, String patronymic, String email, String phone) {
        this.idowner = idowner;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
    }

    public Long getIdowner() {
        return idowner;
    }

    public void setIdowner(Long idowner) {
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

    public Set<PropertyEntity> getPropertyEntitySet() {
        return propertyEntitySet;
    }

    public void setPropertyEntitySet(Set<PropertyEntity> propertyEntitySet) {
        this.propertyEntitySet = propertyEntitySet;
    }
}
