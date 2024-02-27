package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    @Query("select holiday from Holiday holiday where holiday.name=:name")
    Optional<Holiday> findByName(@Param("name") String name);
    @Query("SELECT h FROM Holiday h WHERE h.user.id = :userId")
    List<Holiday> findAllHolidaysByUserId(@Param("userId") Long userId);
    @Query("select holiday from Holiday holiday where upper(holiday.name) like concat('%',:text,'%')")
    List<Holiday> searchHolidayByName(@Param("text") String text);
}
