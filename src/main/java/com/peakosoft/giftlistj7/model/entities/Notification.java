package com.peakosoft.giftlistj7.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notifications")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private boolean isExpand=false;
    private LocalDate createDate;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "notifications")
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gift_id")
    private Gift gift;
}
