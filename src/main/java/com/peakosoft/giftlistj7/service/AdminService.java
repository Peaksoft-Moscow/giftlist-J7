package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Role;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public String blockUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found by email = " + email)
        );
        user.setRole(Role.BLOCKED);
        userRepository.save(user);
        return "user successfully blocked";
    }

    public String unblockUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found by email = " + email)
        );
        user.setRole(Role.USER);
        userRepository.save(user);
        return "user successfully unblocked";
    }

    public String deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found by email = :" + email)
        );
        userRepository.delete(user);
        return "user successfully";
    }


}
