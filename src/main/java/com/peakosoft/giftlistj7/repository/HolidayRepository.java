package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    @Query("select holiday from Holiday holiday where holiday.user.id=:id")
    Optional<List<Holiday>> findAllHolidaysByUserId(@Param("id") Long id);
    @Query("select Holiday from Holiday holiday where holiday.name=:name")
    Holiday findByName(@Param("name") String name);
}