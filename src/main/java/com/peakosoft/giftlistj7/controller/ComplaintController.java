package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.ComplaintRequest;
import com.peakosoft.giftlistj7.model.dto.ComplaintResponse;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;
    @PostMapping("/save/{id}")
    public ComplaintResponse save(@PathVariable Long id, @RequestBody ComplaintRequest complaintRequest, Principal principal) {
        return complaintService.save(id, complaintRequest, principal);
    }

    @GetMapping
    public List<ComplaintResponse> findAll() {
        return complaintService.findAll();
    }
    @GetMapping("/{id}")
    public ComplaintResponse findById(@PathVariable("id") Long id) {
        return complaintService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return complaintService.deleteById(id);
    }
    @GetMapping("/search")
    @Operation(summary = "search complaints by their  names")
    public List<ComplaintResponse> searchComplaintByName(@RequestParam(name = "text",required = false)String text){
        return complaintService.searchComplaintByName(text);
    }
}
