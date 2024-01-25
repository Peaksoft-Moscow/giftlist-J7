package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Complaint;
import com.peakosoft.giftlistj7.model.entities.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Gift, Long> {
    @Query("select complate from Gift complate where complate.user.id=:id and complate.complaints=:null")
    List<Complaint> findAllByUser(Long id);
}
