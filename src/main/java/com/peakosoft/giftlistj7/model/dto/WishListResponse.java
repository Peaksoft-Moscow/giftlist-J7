package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.MyHoliday;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class WishListResponse {
    private String image;
    private String nameOfGift;
    private MyHoliday myHoliday;
    private LocalDate createDate;
    private BookingStatus bookingStatus;
}
