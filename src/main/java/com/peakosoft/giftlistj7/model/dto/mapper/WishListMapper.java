package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import org.springframework.stereotype.Component;

@Component
public class WishListMapper {

    public Gift mapToEntity(WishListRequest wishListRequest) {
        Gift gift = new Gift();
        gift.setImage(wishListRequest.getImage());
        gift.setName(wishListRequest.getName());
        gift.setLink(wishListRequest.getLink());
        gift.setDateOfHoliday(wishListRequest.getDateOfHoliday());
        gift.setDescription(wishListRequest.getDescription());
        return gift;
    }
    public WishListResponse mapToResponse(Gift gift) {
        return WishListResponse.builder()
                .image(gift.getImage())
                .nameOfGift(gift.getName())
                .myHoliday(gift.getHoliday().getName())
                .createDate(gift.getAddDate())
                .bookingStatus(gift.getBookingStatus())
                .build();
    }
}
