package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class BookingResponse {
    private Long id;
    private String image;
    private String name;
    private LocalDate createDate;
    private BookingStatus bookingStatus;
    private User user;
}
