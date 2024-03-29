package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.enums.City;
import com.peakosoft.giftlistj7.model.enums.ClothesSize;
import com.peakosoft.giftlistj7.model.enums.ShoesSize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class FriendInfoResponse {
    private Long id;
    private String image;
    private String name;
    private String lastName;
    private City city;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private String hobby;
    private String importantToKnow;
    private ClothesSize clothesSize;
    private ShoesSize shoesSize;
    private List<WishListResponse> wishLists;
    private List<Holiday> holidays;
    private List<Gift> charity;
}
