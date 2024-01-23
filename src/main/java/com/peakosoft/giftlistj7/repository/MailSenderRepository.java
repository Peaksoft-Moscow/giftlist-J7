package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.MailSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSenderRepository extends JpaRepository<MailSender,Long> {
}
