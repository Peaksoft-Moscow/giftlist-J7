package com.peakosoft.giftlistj7.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "gifts")
@Getter
@Setter
@NoArgsConstructor
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
