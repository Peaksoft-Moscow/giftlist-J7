package com.peakosoft.giftlistj7.model.entities;

import java.time.LocalDate;

public class Gift {
    private Long id;
    private String name;
    private String link;
    private String description;
    private LocalDate addDate;
    private LocalDate dateOfHoliday;
    private Condition condition;
    private GiftStatus giftStatus;
    private BookingStatus bookingStatus;
    private Booking booking;
    private User user;
    private Category category;
    List<Complaint> complaints;
    List<MyHoliday> myHolidays;
}
