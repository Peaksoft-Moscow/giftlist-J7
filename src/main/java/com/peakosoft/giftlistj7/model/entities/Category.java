package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Clothes clothes;
    private Shoes shoes;
    private HouseAndGarden houseAndGarden;
    private SchoolRequirements schoolRequirements;
    private Transport transport;
    private Electronic electronic;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "category")
    private List<Gift> gifts;
}
