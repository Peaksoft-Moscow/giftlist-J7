package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<Gift, Long> {
    @Query("select gift from Gift gift where gift.user.id =: id")
    Optional<List<Gift>> findAllByUserId(@Param("id") Long id);
}