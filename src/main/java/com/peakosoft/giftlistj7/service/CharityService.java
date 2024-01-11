package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.CharityMapper;
import com.peakosoft.giftlistj7.model.entities.Category;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.CategoryRepository;
import com.peakosoft.giftlistj7.repository.CharityRepository;
import com.peakosoft.giftlistj7.repository.SubCategoryRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class CharityService {
    private final CharityRepository charityRepository;
    private final CharityMapper charityMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;


    public CharityResponse createCharity(CharityRequest charityRequest) {
        Gift gift = charityMapper.mapToEntity(charityRequest);
//        User user=userRepository.findByEmail(principal.getName()).orElseThrow(()->new RuntimeException("Not found user with email: " + principal.getName()));
        Category category = categoryRepository.findById(charityRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Not found gift with this id"));
//        SubCategory subCategory=subCategoryRepository.findById(charityRequest.getSubCategoryId()).orElseThrow(()->new RuntimeException("Not found gift with this id"));
        gift.setCategory(category);
//        gift.setUser(user);
        charityRepository.save(gift);
        return charityMapper.mapToResponse(gift);
    }


//    public List<CharityResponse> findAll(Principal principal){
//        User user=userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user with email: " + principal.getName()));
//        List<Gift> myGifts=charityRepository.findAllByUserId(user.getId());
//        
//        }
}
