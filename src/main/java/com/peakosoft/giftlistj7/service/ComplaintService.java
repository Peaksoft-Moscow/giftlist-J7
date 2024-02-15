package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.NotFoundException;
import com.peakosoft.giftlistj7.model.dto.ComplaintRequest;
import com.peakosoft.giftlistj7.model.dto.ComplaintResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.ComplaintMapper;
import com.peakosoft.giftlistj7.model.entities.Complaint;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.ComplaintsRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final UserRepository userRepository;
    private final ComplaintsRepository complaintRepository;
    private final WishListRepository wishListRepository;
    private final ComplaintMapper complaintMapper;
    private final MailSenderService mailSenderService;

    public ComplaintResponse save(Long giftId, ComplaintRequest complaintRequest, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        Gift gift = wishListRepository.findById(giftId).orElseThrow(() -> new NotFoundException("Not found gift by id: " + giftId));
        Complaint complaint = complaintMapper.mapToEntity(complaintRequest);
        complaint.setGift(gift);
        complaint.setUser(user);
        mailSenderService.sendComplaints(principal.getName(), "Received a complaint!", "User with id: (" + user.getId() + ") send complaint to gift with id: (" + giftId + ").");
        complaintRepository.save(complaint);
        return complaintMapper.mapToResponse(complaint);
    }

    public List<ComplaintResponse> findAll() {
        List<Complaint> complaints = complaintRepository.findAll();
        return complaints.stream().map(complaintMapper::mapToResponse).toList();
    }

    public ComplaintResponse findById(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found complaint by id: " + id));
        return complaintMapper.mapToResponse(complaint);
    }

    public String deleteById(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found complaint by id: " + id));
        complaintRepository.delete(complaint);
        return "The complaint was successfully removed!";
    }
}
