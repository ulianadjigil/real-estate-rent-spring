package com.example.realestaterent.repository;

import com.example.realestaterent.entity.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepo extends PagingAndSortingRepository<AddressEntity, Long> {
    @Query(value = "SELECT idaddress FROM Address a WHERE a.country = ?1 and a.region = ?2 " +
            "and a.city = ?3 and a.street = ?4 and a.house_number = ?5", nativeQuery = true)
    List<Long> findAddress(String country, String region, String city, String street, String houseNumber);
}
