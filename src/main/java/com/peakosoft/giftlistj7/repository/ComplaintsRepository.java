package com.peakosoft.giftlistj7.repository;


import com.peakosoft.giftlistj7.model.entities.Complaint;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComplaintsRepository extends JpaRepository<Complaint, Long> {
    @Query("select complaint from Complaint complaint where upper(complaint.complaintName) like concat('%',:text,'%')")
    List<Complaint> searchComplaintByName(@Param("text")String text);
}
