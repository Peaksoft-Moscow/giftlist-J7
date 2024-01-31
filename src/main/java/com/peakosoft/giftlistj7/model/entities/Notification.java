package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.NotificationStatus;
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
    private String giftName;
    private NotificationStatus notificationStatus;
    private String image;
    private boolean isRead=false;
    @Column(name = "create_date")
    private LocalDate createDate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<User>receivers;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gift_id")
    private Gift gift;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wishList_id")
    private Gift wishList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="complaints_id")
    private Complaints complaints;


}
