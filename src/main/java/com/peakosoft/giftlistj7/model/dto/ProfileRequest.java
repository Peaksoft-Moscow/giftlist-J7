package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.ClothesSize;
import com.peakosoft.giftlistj7.model.enums.ShoesSize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String lastName;
    private String firstName;
    private String country;
    private String birthday;
    private String email;
    private String phoneNumber;
    private ClothesSize clothesSize;
    private ShoesSize shoesSize;
    private String hobby;
    private String importantKnow;

}
