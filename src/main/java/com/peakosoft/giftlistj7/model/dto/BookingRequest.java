package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class BookingRequest {
    private String name;
    private LocalDate createDate;
    private BookingStatus bookingStatus;
}
