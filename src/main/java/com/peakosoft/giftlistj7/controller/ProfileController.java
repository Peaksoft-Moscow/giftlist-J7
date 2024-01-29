package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.*;
import com.peakosoft.giftlistj7.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ProfileResponse findById(@PathVariable("id") Long id) {
        return profileService.findById(id);
    }
    @PutMapping("update/{id}")
    public ProfileResponse update(@PathVariable("id") Long profileId, @RequestBody ProfileRequest profileRequest, Principal principal) {
        return profileService.update(profileId, profileRequest, principal);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        profileService.removeById(id, principal);
        return "Gift with id: " + id + " successfully deleted";
    }

}
