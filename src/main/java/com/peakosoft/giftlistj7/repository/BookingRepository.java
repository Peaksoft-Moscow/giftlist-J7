package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("select boking from Booking boking where boking.name=:name")
    Optional<Booking> findByName(@Param("name") String name );

}

