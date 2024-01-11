package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.friends")
    List<User> findAll(@Param("id") Long id);
}