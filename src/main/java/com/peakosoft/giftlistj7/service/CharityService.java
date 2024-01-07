package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.CharityMapper;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import com.peakosoft.giftlistj7.repository.CharityRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CharityService {
    private final CharityRepository charityRepository;
    private final CharityMapper charityMapper;
    private final UserRepository userRepository;

    public CharityResponse save(CharityRequest charityRequest, Principal principal){
        User user=userRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Not found user with email: "+principal.getName()));
        Gift gift=charityMapper.mapToEntity(charityRequest);
        gift.setGiftStatus(GiftStatus.CHARITY);
        gift.set
    }
}
