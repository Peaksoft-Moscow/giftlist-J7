package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.ComplaintRequest;
import com.peakosoft.giftlistj7.model.dto.ComplaintResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.ComplaintMapper;
import com.peakosoft.giftlistj7.model.entities.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintMapper complaintMapper;
    public ComplaintResponse save(Long giftId, ComplaintRequest complaintRequest, Principal principal) {
        Complaint complaint = complaintMapper.mapToEntity(complaintRequest);
        return null;
    }
}
