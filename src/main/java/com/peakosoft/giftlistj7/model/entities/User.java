package com.peakosoft.giftlistj7.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String password;
    private String hobby;
    private String important;
    private String email;
    private Country country;
    private ClotheSize clotheSize;
    private ShoesSize shoesSize;
    private SocialMedia socialMedia;
    private Role role;
    private Booking booking;
    private List<Notification> notifications;
    private List<Complaint> complaints;
    private List<Gift> gifts;
    private List<MyHoliday> myHolidays;

}
