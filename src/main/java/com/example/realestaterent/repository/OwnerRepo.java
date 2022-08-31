package com.example.realestaterent.repository;

import com.example.realestaterent.entity.OwnerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepo extends PagingAndSortingRepository<OwnerEntity, Long> {
    OwnerEntity findOwnerByEmail(String email);
    OwnerEntity findOwnerByPhone(String phone);
    @Query(value = "SELECT idowner FROM owner o WHERE o.name = :ownerName and o.surname = :ownerSurname ;", nativeQuery = true)
    List<Long> findByNameSurname(@Param("ownerName") String name, @Param("ownerSurname") String surname);


    /*@Query(value = "SELECT idowner FROM owner o WHERE o.name = :ownerName and o.surname = :ownerSurname ;", nativeQuery = true)
    List<OwnerEntity> findWithSorting(@Param("ownerName") String name, @Param("ownerSurname") String surname);*/
}
