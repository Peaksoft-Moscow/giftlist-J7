package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WishListRequest {
    private String image;
    private String name;
    private String link;
    private String holidayName;
    private LocalDate dateOfHoliday;
    private String description;
    private BookingStatus bookingStatus;
}
