package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import org.springframework.stereotype.Component;

@Component
public class WishListMapper {

    public Gift mapToEntity(WishListRequest wishListRequest) {
        Gift gift = new Gift();
        gift.setImage(wishListRequest.getImage());
        gift.setName(wishListRequest.getName());
        gift.setLink(wishListRequest.getLink());
        gift.setGiftStatus(GiftStatus.WISHLIST);
        gift.setDateOfHoliday(wishListRequest.getDateOfHoliday());
        gift.setDescription(wishListRequest.getDescription());
        gift.setBookingStatus(BookingStatus.UNBOOKED);
        return gift;
    }

    public WishListResponse mapToResponse(Gift gift) {
        return WishListResponse.builder()
                .id(gift.getId())
                .image(gift.getImage())
                .nameOfGift(gift.getName())
                .myHoliday(gift.getHoliday().getName())
                .dateOfHoliday(gift.getDateOfHoliday())
                .bookingStatus(gift.getBookingStatus())
                .build();
    }
}
