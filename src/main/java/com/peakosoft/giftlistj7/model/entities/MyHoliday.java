package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.Holiday;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "myHolidays")
@NoArgsConstructor
public class MyHoliday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private Holiday holiday;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "gift_id")
    private Gift gift;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
}
