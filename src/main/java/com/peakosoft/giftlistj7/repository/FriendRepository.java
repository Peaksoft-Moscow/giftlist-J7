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
    @Query("select requestFriends from User user join user.requestToFriends requestFriends where user.id=:id")
    List<User> findAllRequestsFriends(@Param("id") Long userId);
    @Query("select friends from User user join user.friends friends where user.id=:id")
    List<User> findAllFriends(@Param("id") Long userId);
    @Query("select friends from User user join user.friends friends where friends.id=:friendId and user.id=:userId")
    User findFriendById(@Param("userId") Long userId,@Param("friendId") Long friendId);
}