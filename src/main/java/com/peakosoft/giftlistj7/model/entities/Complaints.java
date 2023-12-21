package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
public class Complaints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private BookingStatus bookingStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private User users;

    private Gift gifts;
}
