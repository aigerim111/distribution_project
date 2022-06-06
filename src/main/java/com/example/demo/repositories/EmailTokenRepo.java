package com.example.demo.repositories;

import com.example.demo.entities.EmailToken;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTokenRepo extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByTokenId(Long id);
    Optional<EmailToken> findByUser(User user);
    Optional<EmailToken> findByToken(String token);

}
