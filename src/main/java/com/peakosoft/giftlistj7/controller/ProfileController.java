package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.*;
import com.peakosoft.giftlistj7.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Tag(name = "Profile controller",description = "profile" )
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/{id}")
    @Operation(summary = "find by id to to profile")
    public ProfileResponse findById(@PathVariable("id") Long id) {
        return profileService.findById(id);
    }
    @PutMapping("update")
    @Operation(summary = "update requests by principal")
    public ProfileResponse update(@RequestBody ProfileRequest profileRequest, Principal principal) {
        return profileService.update(profileRequest, principal);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete by users id")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        profileService.removeById(id, principal);
        return "Gift with id: " + id + " successfully deleted";
    }
    @RequestMapping(value = "http://localhost:8080/api/profile/logout",method ={RequestMethod.GET,RequestMethod.POST})
    @Operation(summary = "logout current user")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return ResponseEntity.ok("Logged out seccessfuly");
    }
}
