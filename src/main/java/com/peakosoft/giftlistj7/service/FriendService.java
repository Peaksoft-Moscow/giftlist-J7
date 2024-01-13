package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserRepository userRepository;
    public FriendResponse sendRequestToFriend(Long friendId, Principal principal) {
        User friend = userRepository.findById(friendId).orElseThrow(()-> new RuntimeException("Not found friend by id: " + friendId));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Not found user by email: " + principal.getName()));
        for (User userFriend : user.getFriends()) {
            if (userFriend.getId() == friend.getId()) {
                user.getFriends().add(friend);
                userRepository.save(user);
            }
        }
        return friend;
    }
}
