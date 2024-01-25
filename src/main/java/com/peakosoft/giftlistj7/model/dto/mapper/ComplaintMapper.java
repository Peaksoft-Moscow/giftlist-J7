package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.ComplaintRequest;
import com.peakosoft.giftlistj7.model.dto.ComplaintResponse;
import com.peakosoft.giftlistj7.model.entities.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ComplaintMapper {
    public Complaint mapToEntity(ComplaintRequest complaintRequest) {
        Complaint complaint = new Complaint();
        complaint.setComplaintName(complaintRequest.getComplaints());
        complaint.setCreateDate(LocalDate.now());
        return complaint;
    }

    public ComplaintResponse mapToResponse(Complaint complaint) {
        return ComplaintResponse.builder()
                .gift(complaint.getGift())
                .user(complaint.getUser())
                .complaints(complaint.getComplaintName())
                .createDate(LocalDate.now())
                .build();
    }
}
