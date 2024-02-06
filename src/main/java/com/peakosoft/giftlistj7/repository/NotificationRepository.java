package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n from Notification n join n.receivers u where u.id=:id")
    List<Notification> getAllNotification(@Param("id") Long id);

    @Query("select n from Notification n join n.receivers r where n.isRead=false and r.id=:id")
    List<Notification> getAllUnReadNotification(@Param("id") Long id);

    @Query("select n from Notification n join n.receivers r where n.isRead=true and r.id=:id")
    List<Notification> getAllIsReadNotification(@Param("id") Long id);

    @Query("select count (n) from Notification n join  n.receivers r where r.id=:id")
    long countByUserId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Notification n where n in (select n from Notification n join n.receivers r where r.id=:id)")
    void deleteByUserId(@Param("id") Long id);


}
