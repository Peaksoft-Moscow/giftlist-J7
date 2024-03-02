package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Mailing;
<<<<<<< HEAD
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailingRepository extends JpaRepository<Mailing,Long> {

=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends JpaRepository<Mailing,Long> {
>>>>>>> ca6d3ece3ef480d6a22e8d637bfa1cacbb7112c0
}
