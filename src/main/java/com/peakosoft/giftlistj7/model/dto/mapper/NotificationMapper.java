package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.NotificationResponse;
import com.peakosoft.giftlistj7.model.entities.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .name(notification.getGiftName())
                .giftName(notification.getGiftName())
                .status(notification.getStatus())
                .isRead(notification.isRead())
                .createDate(notification.getCreateDate())
                .build();
    }
}
