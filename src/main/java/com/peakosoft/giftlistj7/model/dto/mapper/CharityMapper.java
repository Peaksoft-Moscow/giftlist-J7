package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CharityMapper {
    public Gift mapToEntity(CharityRequest charityRequest){
        Gift gift=new Gift();
        gift.setGiftStatus(GiftStatus.CHARITY);
        gift.setName(charityRequest.getGiftName());
        gift.setImage(charityRequest.getImage());
        gift.setDescription(charityRequest.getDescription());
        gift.setBookingStatus(BookingStatus.BOOKED);
        gift.setSubCategory(charityRequest.getSubCategory());
//        gift.setCategory(charityRequest.getCategory());
        gift.setCondition(charityRequest.getCondition());
        return gift;
    }
    public CharityResponse mapToResponse(Gift gift){
        return CharityResponse.builder()
                .id(gift.getId())
                .image(gift.getImage())
                .giftName(gift.getName())
                .addDate(LocalDate.now())
                .bookingStatus(gift.getBookingStatus())
                .condition(gift.getCondition())
                .user(gift.getUser())
//                .category(gift.getCategory())
                .subCategory(gift.getSubCategory())
                .build();
    }
}
