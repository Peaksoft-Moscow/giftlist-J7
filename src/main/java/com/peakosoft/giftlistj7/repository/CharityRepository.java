package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Gift, Long> {
    @Query("select gift from Gift gift where gift.user.id=:id and gift.giftStatus='CHARITY'")
    List<Gift> findAllByUserId(@Param("id") Long id);

    @Query("select charity from Gift charity where " +
            "upper(charity.category)like concat('%',:text,'%')"+
            "or upper(charity.subCategory) like concat('%',:text,'%')"+
            "or upper(charity.giftStatus)like concat('%',:text,'%')"+
            "or upper(charity.user.country) like concat('%',:text,'%') ")
    List<Gift> searchCharityByName(@Param("status") String status,
                                   @Param("category") String category,
                                   @Param("subCategory") String subCategory,
                                   @Param("country") String country);

}
