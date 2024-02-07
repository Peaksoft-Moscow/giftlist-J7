package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.Category;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GiftResponse {
    private Long id;
    private String name;
    private GiftStatus giftStatus;
    private AuthResponse user;
    private HolidayResponse holiday;
    private Category category;
    private SubCategory subCategory;
}
