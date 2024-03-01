package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.AuthRequest;
import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/block/{email}")
    public String blockUser(@PathVariable String email) {
        return adminService.blockUser(email);
    }

    @PostMapping("/unblock/{email}")
    public String unblockUser(@PathVariable String email) {
        return adminService.unblockUser(email);
    }

    @PostMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        return adminService.deleteUser(email);
    }


}
