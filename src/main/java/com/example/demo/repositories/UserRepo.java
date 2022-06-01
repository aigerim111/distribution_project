package com.example.demo.repositories;

import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>  {

    Optional<User> findByUserId(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findAllByNameKzOrNameRu(String nameKz, String nameRu);

    Optional<User> findByActivationCode(String code);
}
