package com.example.demo.repositories;

import com.example.demo.entities.DictUserType;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>  {

    Optional<User> findByUserId(Long id);
    List<User> findAllByNameKzOrNameRu(String nameKz, String nameRu);
    List<User> findAllByOrgOrDictUserType(Organization org, DictUserType dictUserType, Pageable pageable);
    List<User> findAllByOrgAndDictUserType(Organization org, DictUserType dictUserType, Pageable pageable);
    Optional<User> findByEmailOrPhone(String email, String phone);
    Optional<User> findByLogin(Long loginId);
}
