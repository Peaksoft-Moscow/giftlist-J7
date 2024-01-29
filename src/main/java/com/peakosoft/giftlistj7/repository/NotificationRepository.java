package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n from Notification n join n.receivers u where u.id=?1")
    List<Notification> getAllNotification(Long id);

    @Query("select n from Notification n join n.receivers r where n.isRead=false and r.id=?1")
    List<Notification> getAllUnReadNotification(Long id);

    @Query("select n from Notification n join n.receivers r where n.isRead=true and r.id=?1")
    List<Notification> getAllisUnReadNotification(Long id);

    @Query("select count (n) from Notification n join  n.receivers r where r.id=?1")
    long countByUserId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Notification n where n in (select n from Notification n join n.receivers r where r.id=?1)")
    void deleteByUserId(Long id);


}
