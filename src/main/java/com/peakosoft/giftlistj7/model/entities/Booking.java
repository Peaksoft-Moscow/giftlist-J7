package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private LocalDate createDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
}
