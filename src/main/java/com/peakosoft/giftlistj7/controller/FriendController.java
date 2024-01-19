package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping("/send-request/{id}")
    public FriendResponse sendRequestToFriends(@PathVariable("id") Long friendId, Principal principal) {
        return friendService.sendRequestToFriend(friendId, principal);
    }

    @PostMapping("/accept-request/{id}")
    public FriendResponse acceptRequestToFriend(@PathVariable("id")Long friendId, Principal principal) {
        return friendService.acceptRequestToFriend(friendId, principal);
    }

    @DeleteMapping("/cancel-request/{id}")
    public FriendResponse cancelRequestToFriend(@PathVariable("id")Long friendId, Principal principal) {
        return friendService.cancelRequestToFriend(friendId, principal);
    }
    @DeleteMapping("/{id}")
    public String deleteFriend(@PathVariable("id")Long friendId, Principal principal){
        friendService.deleteFriend(friendId, principal);
        return "Friend has been successfully deleted!";
    }
    @GetMapping("/find-all-requests")
    public List<FriendResponse> findAllRequestsFriends(Principal principal) {
        return friendService.findAllRequestsFriends(principal);
    }
    @GetMapping("/find-all-friends")
    public List<FriendResponse> findAllFriends(Principal principal) {
        return friendService.findAllFriends(principal);
    }
    @GetMapping("/{id}")
    public FriendInfoResponse findById(@PathVariable("id") Long friendId, Principal principal){
        return friendService.findById(friendId, principal);}

}
