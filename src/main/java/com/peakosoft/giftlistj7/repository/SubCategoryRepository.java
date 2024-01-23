package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select subCategory from SubCategory subCategory where subCategory.name=:name")
    Optional<SubCategory> findByName(@Param("name") String name);
}
