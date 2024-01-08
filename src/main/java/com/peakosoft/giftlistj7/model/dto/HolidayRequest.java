package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HolidayRequest {
    private String name;
    private String description;
    private String image;


}