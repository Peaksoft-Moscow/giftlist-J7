package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.Category;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.Condition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CharityResponse {
    private Long id;
    private String image;
    private Condition condition;
    private BookingStatus bookingStatus;
    private LocalDate addDate;
    private String giftName;
    private User user;
    private Category category;
    private SubCategory subCategory;
}
