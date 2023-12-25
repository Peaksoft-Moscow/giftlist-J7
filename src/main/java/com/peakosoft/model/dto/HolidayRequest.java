package com.peakosoft.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HolidayRequest {
    private String name;
    private String image;
    private String ownerName;

}
