package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.Condition;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gifts")
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
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Enumerated(EnumType.STRING)
    private GiftStatus giftStatus;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private Booking booking;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade ={CascadeType.ALL},mappedBy = "gift")
    private List<Complaint> complaints;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "gift")
    private List<Holiday> myHolidays;

}
