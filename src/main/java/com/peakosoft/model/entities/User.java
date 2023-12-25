package com.peakosoft.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "last_name")
    String lastName;
    String email;
    String hobby;
    @Column(name = "phone_number")
    String phoneNumber;
    String password;
    @Column(name = "important_information")
    String importantInformation;
    @Column(name = "bitch_day")
    String bitchDay;
    LocalDate createDate;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "user")
    List<Holiday>holidays;

}
