package com.peakosoft.giftlistj7.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "myHolidays")
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String holidayName;
    private String image;
    private String description;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "myHoliday")
    private List<Gift> gifts;
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Gift> wishlist;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

}
