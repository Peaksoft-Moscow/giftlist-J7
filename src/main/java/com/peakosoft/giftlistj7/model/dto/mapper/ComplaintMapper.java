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
    private final UserMapper userMapper;
    private final GiftMapper giftMapper;
    public Complaint mapToEntity(ComplaintRequest complaintRequest) {
        Complaint complaint = new Complaint();
        complaint.setComplaintName(complaintRequest.getComplaints());
        complaint.setDescription(complaintRequest.getDescription());
        complaint.setCreateDate(LocalDate.now());
        return complaint;
    }

    public ComplaintResponse mapToResponse(Complaint complaint) {
        return ComplaintResponse.builder()
                .id(complaint.getId())
                .gift(giftMapper.mapToResponse(complaint.getGift()))
                .user(userMapper.mapToResponse(complaint.getUser()))
                .complaints(complaint.getComplaintName())
                .description(complaint.getDescription())
                .createDate(LocalDate.now())
                .build();
    }
}
