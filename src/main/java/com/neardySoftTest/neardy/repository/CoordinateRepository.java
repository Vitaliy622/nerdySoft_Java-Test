package com.neardySoftTest.neardy.repository;


import com.neardySoftTest.neardy.models.Coordinates;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends PagingAndSortingRepository<Coordinates, Long>,
        JpaSpecificationExecutor<Coordinates> {
}
