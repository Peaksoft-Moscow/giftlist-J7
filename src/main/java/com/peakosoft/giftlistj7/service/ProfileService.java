package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.EntityNotFoundException;
import com.peakosoft.giftlistj7.model.dto.ProfileRequest;
import com.peakosoft.giftlistj7.model.dto.ProfileResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.ProfileMapper;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Country;
import com.peakosoft.giftlistj7.repository.ProfileRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    public ProfileResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id:" + id));
        return profileMapper.mapToResponse(user);
    }

    public ProfileResponse update(Long userId, ProfileRequest request,Principal principal) {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id:" + userId));
        oldUser.setName(request.getFirstName());
        oldUser.setLastName(request.getLastName());
        oldUser.setBirthday(request.getBirthday());
        oldUser.setEmail(request.getEmail());
        oldUser.setPhoneNumber(request.getPhoneNumber());
        oldUser.setClothesSize(request.getClothesSize());
        oldUser.setShoesSize(request.getShoesSize());
        oldUser.setHobby(request.getHobby());
        oldUser.setImportant(request.getImportantKnow());
        if (request.getCountry() != null || request.getCountry() == null) {
            oldUser.setCountry(Country.valueOf(request.getCountry()));
        }
        userRepository.save(oldUser);
        return profileMapper.mapToResponse(oldUser);
    }

    public void removeById(Long id, Principal principal) {
        User user = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        User user1 = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("not found by email" + principal.getName()));
        if (user1.getId() == user.getId()) {
            profileRepository.delete(user);
        }
    }
}
