package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class BookingRequest {
    private Long id;
    private String image;
    private String giftName;
    private GiftStatus giftStatus;
    private LocalDate createDate;
    private BookingStatus bookingStatus;
}
