package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.NotFoundException;
import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.FriendMapper;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.FriendRepository;
import com.peakosoft.giftlistj7.repository.NotificationRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendMapper friendMapper;
    private final MailSenderService mailSenderService;
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    public FriendResponse sendRequestToFriend(Long friendId, Principal principal) {
        User friend = userRepository.findById(friendId).orElseThrow(() -> new NotFoundException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        if (Objects.equals(user.getId(), friendId)) throw new RuntimeException("You can't send a request to yourself!");
        for (User userFriend : user.getFriends()) {
            if (Objects.equals(userFriend.getId(), friend.getId()))
                throw new RuntimeException("This user is already your friend!");
        }
        for (User requestToFriend : user.getRequestToFriends()) {
            if (Objects.equals(requestToFriend.getId(), friend.getId()))
                throw new RuntimeException("You have already sent a request to this user!");
        }
        user.getRequestToFriends().add(friend);
        mailSenderService.sendToFriends(friend.getEmail(), "friend request", user.getName() + " " + user.getLastName() + " has sent you a friend request.");
        friend.setNotification(notificationService.sendNotification(new ArrayList<>()));
        notificationRepository.save(friend.getNotification());
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public FriendResponse acceptRequestToFriend(Long friendId, Principal principal) {
        User friend = friendRepository.findById(friendId).orElseThrow(() -> new NotFoundException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        for (User requestToFriend : user.getRequestToFriends()) {
            if (!Objects.equals(requestToFriend.getId(), friendId))
                throw new RuntimeException("This user did not send you a friend request!");
        }
        user.getFriends().add(friend);
        friend.getFriends().add(user);
        friend.getRequestToFriends().remove(user);
        mailSenderService.sendToFriends(friend.getEmail(), "Accept request", user.getName() + " " + user.getLastName() + " has accepted your friend request.");
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public FriendResponse cancelRequestToFriend(Long friendId, Principal principal) {
        User friend = userRepository.findById(friendId).orElseThrow(() -> new NotFoundException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        for (User requestToFriend : user.getRequestToFriends()) {
            if (!Objects.equals(requestToFriend.getId(), friend.getId()))
                throw new RuntimeException("This user did not send you a friend request!");
        }
        friend.getRequestToFriends().remove(user);
        mailSenderService.sendToFriends(friend.getEmail(), "Cancel request", user.getName() + " " + user.getLastName() + " has canceled your friend request.");
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public String deleteFriend(Long friendId, Principal principal) {
        User friend = friendRepository.findById(friendId).orElseThrow(() -> new NotFoundException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        for (User userFriend : user.getFriends()) {
            if (!Objects.equals(userFriend.getId(), friend.getId()))
                throw new RuntimeException("This user is not your friend!");
        }
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
        mailSenderService.sendToFriends(friend.getEmail(), "delete friend", user.getName() + " " + user.getLastName() + " has deleted you from friends.");
        friendRepository.save(friend);
        userRepository.save(user);
        return "Friend has been successfully deleted!";
    }

    public List<FriendResponse> findAllRequestsFriends(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        List<User> requestsFriends = friendRepository.findAllRequestsFriends(user.getId());
        return requestsFriends.stream().map(friendMapper::mapToResponse).toList();
    }

    public List<FriendResponse> findAllFriends(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        List<User> friends = friendRepository.findAllFriends(user.getId());
        return friends.stream().map(friendMapper::mapToResponse).toList();
    }

    public FriendInfoResponse findById(Long friendId, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not found user by email: " + principal.getName()));
        User friend = friendRepository.findFriendById(friendId, user.getId());
        return friendMapper.mapToInfoResponse(friend);
    }
}
