package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WishListResponse {
    private Long id;
    private String image;
    private String nameOfGift;
    private String myHoliday;
    private LocalDate dateOfHoliday;
    private BookingStatus bookingStatus;
    private LocalDate addDate;
}
