package com.peakosoft.giftlistj7.service;
import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.CharityMapper;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.repository.CategoryRepository;
import com.peakosoft.giftlistj7.repository.CharityRepository;
import com.peakosoft.giftlistj7.repository.SubCategoryRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CharityService {
    private final CharityRepository charityRepository;
    private final CharityMapper charityMapper;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;



    public CharityResponse save(CharityRequest charityRequest, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user with email: " + principal.getName()));
        Gift gift = charityMapper.mapToEntity(charityRequest);
        System.out.println("Это имя сабкатегории"+charityRequest.getSubCategoryName());
        gift.setUser(user);
        charityRepository.save(gift);
        return charityMapper.mapToResponse(gift);
    }

    public List<CharityResponse> findAll(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user with email:" + principal.getName()));
        List<Gift> gifts = charityRepository
                .findAllByUserId(user.getId());
        return gifts.stream().map(charityMapper::mapToResponse).toList();
    }

    public CharityResponse update(Long giftId, CharityRequest charityRequest, Principal principal) {
        Gift oldGift = charityRepository.findById(giftId).orElseThrow(() -> new RuntimeException("Not found gift by id: " + giftId));
        SubCategory subCategory = subCategoryRepository.findByName(charityRequest.getSubCategoryName()).orElseThrow(() -> new RuntimeException("Not found subCategory with this name"));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user by email: " + principal.getName()));
        if (user.getEmail() == oldGift.getUser().getEmail()) {
            oldGift.setImage(charityRequest.getImage());
            oldGift.setName(charityRequest.getGiftName());
            oldGift.setCondition(charityRequest.getCondition());
            oldGift.setDescription(charityRequest.getDescription());
            oldGift.setSubCategory(subCategory);
            oldGift.setBookingStatus(BookingStatus.BOOKED);
            charityRepository.save(oldGift);

        }
    return charityMapper.mapToResponse(oldGift);
    }
    public void delete(Long giftId,Principal principal){
        User user2=userRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Not found user by email: " + principal.getName()));
        Gift gift=charityRepository.findById(giftId).orElseThrow(()->new RuntimeException("Not found gift by id: " + giftId));
        if (user2.getEmail()==gift.getUser().getEmail()){
            charityRepository.delete(gift);
        }
    }
}