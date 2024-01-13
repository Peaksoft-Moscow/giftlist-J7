package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendController {
    public FriendResponse sendRequestToFriends(Long friendId, Principal principal) {
        return null;
    }
    public FriendResponse acceptRequestToFriend(Long friendId, Principal principal) {
        return null;
    }
    public FriendResponse cancelRequestToFriend(Long friendId, Principal principal) {
        return null;
    }

}
