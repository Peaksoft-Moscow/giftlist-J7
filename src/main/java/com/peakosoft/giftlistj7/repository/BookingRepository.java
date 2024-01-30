package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("select boking from Booking boking where boking.giftName=:name")
    Optional<Booking> findByName(@Param("name") String name );

    @Query("select b from Booking b where b.gift.id =:id")
    Booking findBookingByGiftId(@Param("id") Long id);

    @Query("select b from Booking b where b.user.id =:id")
    List<Booking> findBookingByUserId(@Param("id") Long id);





}

