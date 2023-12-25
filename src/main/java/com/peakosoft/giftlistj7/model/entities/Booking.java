package com.peakosoft.giftlistj7.model.entities;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private String image;
    private String name;
    private LocalDate createDate;
    private BookingStatus bookingStatus;
    private List<User> users;
}
