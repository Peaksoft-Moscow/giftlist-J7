package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
public class CharityController {
    public final CharityService charityService;
    @PostMapping("/save")
    public CharityResponse save(@RequestBody CharityRequest charityRequest, Principal principal){
        return charityService.save(charityRequest,principal);
    }
    @GetMapping
    public List<CharityResponse> findAllByUser(Principal principal){
        return charityService.findAll(principal);
    }
    @PutMapping("/update/{id}")
    public CharityResponse update(@PathVariable ("id") Long id,@RequestBody CharityRequest charityRequest,Principal principal){
        return charityService.update(id, charityRequest, principal);
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id,Principal principal){
        charityService.delete(id,principal);
        return "Gift with id: "+id+" successfully deleted";
    }
}
