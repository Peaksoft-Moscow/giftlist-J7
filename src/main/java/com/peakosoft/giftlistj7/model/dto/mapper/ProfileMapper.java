package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.ProfileRequest;
import com.peakosoft.giftlistj7.model.dto.ProfileResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.Country;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProfileMapper {
    public User mapToEntity(ProfileRequest profileRequest) {
        User user = new User();
        user.setName(profileRequest.getFirstName());
        user.setLastName(profileRequest.getLastName());
        user.setBirthday(profileRequest.getBirthday());
        user.setEmail(profileRequest.getEmail());
        user.setPhoneNumber(profileRequest.getPhoneNumber());
        user.setClothesSize(profileRequest.getClothesSize());
        user.setShoesSize(profileRequest.getShoesSize());
        user.setHobby(profileRequest.getHobby());
        user.setImportant(profileRequest.getImportantKnow());
        if (profileRequest.getCountry() != null) {
            user.setCountry(Country.valueOf(profileRequest.getCountry()));
        }
        return user;
    }
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ProfileResponse mapToResponse(User user) {
        return ProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday().format(dateFormatter))
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .clothesSize(user.getClothesSize())
                .shoesSize(user.getShoesSize())
                .hobby(user.getHobby())
                .importantKnow(user.getImportant())
                .country(user.getCountry().name())
                .build();
    }
}
