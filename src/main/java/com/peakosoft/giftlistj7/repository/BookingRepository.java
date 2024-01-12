package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<String,Long> {
    @Query("select boking from Booking boking where boking.name=:name")
    Optional<Booking> findByName(@Param("name") String name );
}

