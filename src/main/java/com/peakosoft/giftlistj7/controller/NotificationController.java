package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.NotificationResponse;
import com.peakosoft.giftlistj7.model.entities.Notification;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/api/notifications")
@Tag(name = "Notification controller", description = "Uses by user only!")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    NotificationService notificationService;

    @GetMapping("/find-all_notifications")
    @Operation(summary = "Get notification", description = "Getting notification from user")
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return new ResponseEntity<>(notificationService.getAllNotification(), HttpStatus.CREATED);
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @GetMapping("/isread")
    public ResponseEntity<List<NotificationResponse>> getAllIsReadNotifications() {
        return new ResponseEntity<>(notificationService.getAllIsReadNotification(), HttpStatus.OK);
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @GetMapping("/unread")
    public ResponseEntity<List<NotificationResponse>> getAllUnReadNotifications() {
        return new ResponseEntity<>(notificationService.getAllUnReadNotification(), HttpStatus.OK);

    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationByUserId(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(notificationService.getNotificationByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @PutMapping("/mark")
    public ResponseEntity<String> markSeenNotificationAsRead() {
        notificationService.markSeenNotificationAsRead();
        return ResponseEntity.ok("Notification marked as read successfully");
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @DeleteMapping
    public ResponseEntity<String> deleteAllNotifications() {
        notificationService.deleteAllNotification();
        return ResponseEntity.ok("All notifications deleted successfully");
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(@RequestBody List<User> receivers) {
        Notification notification = notificationService.sendNotification(receivers);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @Operation(summary = "Get notification", description = "Getting notification from user")
    @PostMapping("/accept")
    public ResponseEntity<Notification> acceptNotification(@RequestBody List<User> receivers) {
        Notification notification = notificationService.acceptToNotification(receivers);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
