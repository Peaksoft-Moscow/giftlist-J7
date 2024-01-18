package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
}