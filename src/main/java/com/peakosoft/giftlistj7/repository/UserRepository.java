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
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select user from User user where user.name=:name")
    Optional<User> findByName(@Param("name") String name);

    User findByActivationCode(String code);

    @Query("select user from User user where upper(user.name) like concat('%',:text,'%')")
    List<User> searchUserByName(@Param("text") String text);
}