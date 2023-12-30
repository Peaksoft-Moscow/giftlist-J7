package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.*;
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
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private ClothesSize clothesSize;
    @Enumerated(EnumType.STRING)
    private SocialMedia socialMedia;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> booking;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "users_notifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notifications;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Complaint> complaints;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Gift> gifts;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Holiday> myHolidays;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<ShoesSize> shoesSize;

}
