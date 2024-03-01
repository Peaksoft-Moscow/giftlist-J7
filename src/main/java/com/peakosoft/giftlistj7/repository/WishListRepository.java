package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<Gift, Long> {

    @Query("select gift from Gift gift where gift.user.id=:id and gift.giftStatus='WISHLIST'")
    List<Gift> findAllWishListsByUserId(@Param("id") Long id);
    @Query("select gift from Gift gift where upper(gift.name) like concat('%',:text,'%')")
    List<Gift> searchGiftByName(@Param("text") String text);
}