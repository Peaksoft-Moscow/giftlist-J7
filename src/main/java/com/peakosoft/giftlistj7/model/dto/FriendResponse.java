package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FriendResponse {
    private Long id;
    private String image;
    private String name;
    private String lastName;
    private int amountWishes;
    private int amountHolidays;

    public int wishListCount(){
        return 0;
    }
}