package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class HolidayResponse {
    private Long id;
    private String name;
    private LocalDate date;
    private String image;
    private String ownerName;
    private LocalDate createDate;


}
