package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class FriendMapper {
    public FriendResponse mapToResponse(User user) {
        int count = 0;
        for (Gift gift : user.getGifts()) {
            if (gift.getGiftStatus().equals("WISHLISTS")) {
                count++;
            }
        }
        return FriendResponse.builder()
                .id(user.getId())
                .image(user.getImage())
                .name(user.getName())
                .lastName(user.getLastName())
                .amountHolidays(user.getMyHolidays().size())
                .amountWishes(count)
                .build();
    }
}
