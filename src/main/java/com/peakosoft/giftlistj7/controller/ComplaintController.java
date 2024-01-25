package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.ComplaintRequest;
import com.peakosoft.giftlistj7.model.dto.ComplaintResponse;
import com.peakosoft.giftlistj7.model.entities.Complaint;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/complaints")
public class ComplaintController {
    @PostMapping("/save/{id}")
    public ComplaintResponse save(@PathVariable Long id, @RequestBody ComplaintRequest complaintRequest, Principal principal) {
        return null;
    }

    @GetMapping
    public List<ComplaintResponse> findAll(Principal principal) {
        return null;
    }

    @PutMapping("/update/{id}")
    public ComplaintResponse update(@PathVariable Long id, @RequestBody ComplaintRequest complaintRequest, Principal principal) {
        return null;
    }
    @DeleteMapping("/{id}")
    public ComplaintResponse delete(@PathVariable Long id, Principal principal) {
        return null;
    }
}
