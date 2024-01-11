package com.peakosoft.giftlistj7.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    private String image;
    private String name;
    private String lastName;
    private int amountWishes;
    private int amountHolidays;
    private boolean accept;
    private boolean reject;
}
