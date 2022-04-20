package com.example.demo.repositories;

import com.example.demo.entities.Login;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {

    Optional<Login> findByEmail(String email);

}
