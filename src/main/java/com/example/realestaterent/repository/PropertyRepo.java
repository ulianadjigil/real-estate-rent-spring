package com.example.realestaterent.repository;

import com.example.realestaterent.entity.PropertyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PropertyRepo extends PagingAndSortingRepository<PropertyEntity, Long> {
}
