//package com.peakosoft.giftlistj7.model.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "myHolidays")
//@Getter
//@Setter
//@NoArgsConstructor
//public class MyHoliday {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String image;
//
//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "holiday_id")
//    private Holiday holiday;
//    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "myHoliday")
//    private List<Gift> gifts;
//
//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "user_id")
//    private User user;
//}
