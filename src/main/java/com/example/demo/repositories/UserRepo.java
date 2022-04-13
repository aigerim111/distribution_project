package com.example.demo.repositories;

import com.example.demo.entities.DictUserType;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>  {

    Optional<User> findAllByUserId(Long id);
    List<User> findAllByName_ruOrName_z(String name);
    List<User> findAllByOrgOrDictUserType(Organization org, DictUserType dictUserType, Pageable pageable);
    List<User> findAllByOrgAndDictUserType(Organization org, DictUserType dictUserType, Pageable pageable);
    Optional<User> findAllByEmailOrPhone(String email, String phone);
}
