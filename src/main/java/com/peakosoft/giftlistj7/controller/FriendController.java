package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.FriendInfoResponse;
import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
@Tag(name = "Friends controller", description = "")
@SecurityRequirement(name="Authorization")
public class FriendController {
    private final FriendService friendService;

    @PostMapping("/send-request/{id}")
    @Operation(summary = "send requests to friends")
    public FriendResponse sendRequestToFriends(@PathVariable("id") Long friendId, Principal principal) {
        return friendService.sendRequestToFriend(friendId, principal);
    }

    @PostMapping("/accept-request/{id}")
    @Operation(summary = "accept requests from user")
    public FriendResponse acceptRequestToFriend(@PathVariable("id")Long friendId, Principal principal) {
        return friendService.acceptRequestToFriend(friendId, principal);
    }

    @DeleteMapping("/cancel-request/{id}")
    @Operation(summary = "cancel requests from user")
    public FriendResponse cancelRequestToFriend(@PathVariable("id")Long friendId, Principal principal) {
        return friendService.cancelRequestToFriend(friendId, principal);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a user's friend")
    public String deleteFriend(@PathVariable("id")Long friendId, Principal principal){
        friendService.deleteFriend(friendId, principal);
        return "Friend has been successfully deleted!";
    }
    @GetMapping("/find-all-requests")
    @Operation(summary = "find all the user's friends")
    public List<FriendResponse> findAllRequestsFriends(Principal principal) {
        return friendService.findAllRequestsFriends(principal);
    }
    @GetMapping("/find-all-friends")
    @Operation(summary = "find all the friends requests to user")
    public List<FriendResponse> findAllFriends(Principal principal) {
        return friendService.findAllFriends(principal);
    }
    @GetMapping("/{id}")
    @Operation(summary = "find friend by id")
    public FriendInfoResponse findById(@PathVariable("id") Long friendId, Principal principal){
        return friendService.findById(friendId, principal);}

}
