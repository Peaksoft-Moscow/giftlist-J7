package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.NotificationStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String name;
    private String lastName;
    private String giftName;
    private NotificationStatus status;
    private boolean isRead;
    private LocalDate createDate;

    public NotificationResponse(String name) {
        this.name = name;
    }
}
