package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.NotificationResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.NotificationMapper;
import com.peakosoft.giftlistj7.model.entities.Notification;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.NotificationStatus;
import com.peakosoft.giftlistj7.repository.NotificationRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationService {
    UserRepository userRepository;
    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;
    MailSenderService mailSenderService;

    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }

    public List<NotificationResponse> getAllNotification() {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("not found Authentication user"));
        List<Notification> notifications = notificationRepository.getAllNotification(user.getId());
        if (notifications == null || notifications.isEmpty()) {
            throw new RuntimeException("Not found bound Notification");
        } else {
            notificationRepository.saveAll(notifications);
        }
        return notifications.stream()
                .map(notificationMapper::mapToResponse).
                collect(toList());
    }

    public List<NotificationResponse> getAllReadNotification() {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("Not found user Authentication"));
        List<Notification> notifications = notificationRepository.getAllReadNotification(user.getId());
        return notifications.stream()
                .map(notificationMapper::mapToResponse).
                collect(toList());
    }

    public List<NotificationResponse> getAllUnReadNotification() {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("Not found user Authentication"));
        List<Notification> notifications = notificationRepository.getAllUnReadNotification(user.getId());
        if (notifications.isEmpty()) {
            throw new EntityNotFoundException("Not found Read Notification");
        }
        return notifications.stream()
                .map(notificationMapper::mapToResponse).
                collect(toList());
    }

    public NotificationResponse getNotificationByUserId(Long id) {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("Not found user Authentication"));
        Notification notification = (Notification) notificationRepository.getAllNotification(id);
        if (notification == null) {
            throw new EntityNotFoundException("Not found Notification for user wish id");
        }
        notification.setUser(user);
        Objects.requireNonNull(notification).setRead(true);
        notificationRepository.save(notification);
        return notificationMapper.mapToResponse(notification);
    }

    public void markSeenNotificationAsRead() {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("Not found user Authentication"));
        Notification notification = (Notification) notificationRepository.getAllNotification(user.getId());
        if (notification == null) {
            throw new RuntimeException("Not found for user with id" + user.getId());

        }
        Objects.requireNonNull(notification).setRead(true);
        notificationRepository.save(notification);
        notificationMapper.mapToResponse(notification);
    }

    public void deleteAllNotification() {
        User user = getAuthenticatedUser().orElseThrow(
                () -> new EntityNotFoundException("Not found user Authentication"));
        if (notificationRepository.countByUserId(user.getId()) == 0) {
            throw new RuntimeException("Not found user id");
        }
        notificationRepository.deleteByUserId(user.getId());
        new NotificationResponse("Notification delete successfully");
    }

    public Notification sendNotification(List<User> receivers) {
        Notification notification = new Notification();
        notification.setUser(notification.getUser());
        notification.setReceivers(receivers);
        notification.setCreateDate(LocalDate.now());
        notification.setNotificationStatus(NotificationStatus.REQUEST_TO_FRIED);
        mailSenderService.sendNotifications(notification.getUser().getEmail(),"giftList-m7","text");
        return notification;
    }

    public Notification acceptToNotification(List<User> receivers) {
        Notification notification = new Notification();
        notification.setUser(notification.getUser());
        notification.setReceivers(receivers);
        notification.setCreateDate(LocalDate.now());
        notification.setNotificationStatus(NotificationStatus.ACCEPT_REQUEST);
        return notification;
    }

}
