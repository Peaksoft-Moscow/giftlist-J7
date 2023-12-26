package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.Holiday;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishListRequest {
    private String image;
    private String name;
    private String link;
    private Holiday holiday;
    private String dateOfHoliday;
    private String description;
}
