package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import com.peakosoft.giftlistj7.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CharityMapper {
    private final SubCategoryRepository subCategoryRepository;


    public Gift mapToEntity(CharityRequest charityRequest) {
        Gift gift = new Gift();
        SubCategory subCategory = subCategoryRepository.findByName(charityRequest.getSubCategoryName()).orElseThrow(() -> new RuntimeException("Not found subCategory with this name"));
        gift.setGiftStatus(GiftStatus.CHARITY);
        gift.setName(charityRequest.getGiftName());
        gift.setImage(charityRequest.getImage());
        gift.setDescription(charityRequest.getDescription());
        gift.setBookingStatus(BookingStatus.UNBOOKED);
        gift.setSubCategory(subCategory);
        gift.setCondition(charityRequest.getCondition());
        gift.setAddDate(LocalDate.now());
        return gift;
    }

    public CharityResponse mapToResponse(Gift gift) {
        return CharityResponse.builder()
                .id(gift.getId())
                .image(gift.getImage())
                .giftName(gift.getName())
                .bookingStatus(gift.getBookingStatus())
                .condition(gift.getCondition())
                .userName(gift.getUser().getName())
                .userLastName(gift.getUser().getLastName())
                .addDate(gift.getAddDate())
                .subCategory(gift.getSubCategory())
                .build();
    }
}
