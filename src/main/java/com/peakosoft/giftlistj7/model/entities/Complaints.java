package com.peakosoft.giftlistj7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peakosoft.giftlistj7.model.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "complaints")
@NoArgsConstructor
public class Complaints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintName;
    private String description;
    private LocalDate createDate;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    private Gift gift;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complaints")
    private List<Notification> notification;
}
