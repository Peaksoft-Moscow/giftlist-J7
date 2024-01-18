package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.FriendMapper;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.FriendRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendMapper friendMapper;

    public FriendResponse sendRequestToFriend(Long friendId, Principal principal) {
        User friend = userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        if (Objects.equals(user.getId(), friendId)) throw new RuntimeException("You can't send a request to yourself!");
        for (User userFriend : user.getFriends()) {
            if (Objects.equals(userFriend.getId(), friend.getId()))
                throw new RuntimeException("This user is already your friend!");
        }
        user.getRequestToFriends().add(friend);
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public FriendResponse acceptRequestToFriend(Long friendId, Principal principal) {
        System.out.println("Friend id: "+friendId);
        System.out.println("User name "+principal.getName());
        User friend = friendRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
//        User user = getAuthenticatedUser();
        System.out.println("User id: "+ user.getId());
        System.out.println("User name: "+ user.getName());
        user.getRequestToFriends().remove(friend);
        user.getFriends().add(friend);
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public FriendResponse cancelRequestToFriend(Long friendId, Principal principal) {
        User friend = userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        for (User requestToFriend : user.getRequestToFriends()) {
            if (Objects.equals(requestToFriend.getId(), friend.getId())) {
                user.getRequestToFriends().remove(friend);
                friend.getRequestToFriends().remove(user);
            }
        }
        friendRepository.save(friend);
        userRepository.save(user);
        return friendMapper.mapToResponse(friend);
    }

    public String deleteFriend(Long friendId, Principal principal) {
        User friend = friendRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        for (User userFriend : user.getFriends()) {
            if (!Objects.equals(userFriend.getId(), friend.getId())) {
                user.getFriends().remove(friend);
                friend.getFriends().remove(user);
            }
        }
        friendRepository.save(friend);
        userRepository.save(user);
        return "Friend has been successfully deleted!";
    }

    public FriendResponse findAllRequestsFriends(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        List<User> requestsFriends = friendRepository.findAllRequestsFriends(user.getId());
        return (FriendResponse) requestsFriends.stream().map(friendMapper::mapToResponse).toList();
    }

    public FriendResponse findAllFriends(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        List<User> friends = friendRepository.findAllFriends(user.getId());
        return (FriendResponse) friends.stream().map(friendMapper::mapToResponse).toList();
    }

    public FriendInfoResponse findById(Long friendId, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        User friend = friendRepository.findFriendById(friendId, user.getId());
        return friendMapper.mapInfoToResponse(friend);
    }
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).get();
    }
}
