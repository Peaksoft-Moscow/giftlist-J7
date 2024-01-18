package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.service.FriendService;
import com.sun.security.auth.PrincipalComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;


    @GetMapping("/get1")
    public void get(Principal principal){
        System.out.println("get method "+principal.getName());
    }
    @GetMapping("/get2")
    public void get2(Principal principal){
        System.out.println("get method 2 "+principal.getName());
    }

    @PostMapping("/send-request/{id}")
    public FriendResponse sendRequestToFriends(@PathVariable("id") Long friendId, Principal principal) {
        return friendService.sendRequestToFriend(friendId, principal);
    }

    @PostMapping("/accept-request/{id}")
    public FriendResponse acceptRequestToFriend(@PathVariable("id")Long friendId, Principal principal) {
        System.out.println("Controller "+principal);
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
    public FriendResponse findAllRequestsFriends(Principal principal) {
        return friendService.findAllRequestsFriends(principal);
    }
    @GetMapping("/find-all-friends")
    public FriendResponse findAllFriends(Principal principal) {
        return friendService.findAllFriends(principal);
    }
    @GetMapping("/{id}")
    public FriendInfoResponse findById(@PathVariable("id") Long friendId, Principal principal){
        return friendService.findById(friendId, principal);}

}
