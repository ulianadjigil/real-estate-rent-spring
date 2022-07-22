package com.example.realestaterent.repository;

import com.example.realestaterent.entity.OwnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface OwnerRepo extends PagingAndSortingRepository<OwnerEntity, Long> {
    OwnerEntity findOwnerByEmail(String email);
    OwnerEntity findOwnerByPhone(String phone);

}
