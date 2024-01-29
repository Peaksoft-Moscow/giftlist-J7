package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Mailing;
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailingRepository extends JpaRepository<Mailing,Long> {

}
