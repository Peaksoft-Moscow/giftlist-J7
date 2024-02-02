package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendMapper {
    private final WishListRepository wishListRepository;
    private final WishListMapper wishListMapper;
    public FriendResponse mapToResponse(User user) {
        return FriendResponse.builder()
                .id(user.getId())
                .image(user.getImage())
                .name(user.getName())
                .lastName(user.getLastName())
                .amountHolidays(user.getMyHolidays().size())
                .amountWishes(wishListRepository.findAllByUserId(user.getId()).size())
                .build();
    }
    public FriendInfoResponse mapToInfoResponse(User user){
        return FriendInfoResponse.builder()
                .id(user.getId())
                .image(user.getImage())
                .name(user.getName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .hobby(user.getHobby())
                .importantToKnow(user.getImportant())
                .clothesSize(user.getClothesSize())
                .shoesSize(user.getShoesSize())
                .wishLists(wishListRepository.findAllByUserId(user.getId()).stream().map(wishListMapper::mapToResponse).toList())
                .holidays(user.getMyHolidays())
                .build();
    }
}
