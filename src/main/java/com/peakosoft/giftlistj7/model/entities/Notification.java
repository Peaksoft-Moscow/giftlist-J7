package com.peakosoft.giftlistj7.model.entities;

import java.time.LocalDate;

public class Notification {
    private Long id;
    private String image;
    private LocalDate createDate;
    private List<User> users;
    private Gift gift;
}
