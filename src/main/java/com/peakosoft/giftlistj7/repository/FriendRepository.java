package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.dto.FriendResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<User, Long> {
    @Query("select friend from User friend join friend.requestToFriends requestFromFriends where requestFromFriends.id=:id")
    List<User> findAllRequestsFriends(@Param("id") Long userId);
    @Query("select friends from User user join user.friends friends where user.id=:id")
    List<User> findAllFriends(@Param("id") Long userId);
    @Query("select friend from User user join user.friends friend where friend.id=:friendId and user.id=:userId")
    User findFriendById(@Param("userId") Long userId,@Param("friendId") Long friendId);
    @Query("select friend from User user join user.friends where upper(friend.name) like concat('%',:text,'%') and user.id=:userId")
    List<FriendResponse> searchFriendByName(@Param("text")String text,@Param("userId") Long userId);

}