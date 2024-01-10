package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
public class CharityController {
    public final CharityService charityService;
    @PostMapping("/create")
    public CharityResponse createCharity(@RequestBody CharityRequest charityRequest){
        return charityService.createCharity(charityRequest);
    }
}
