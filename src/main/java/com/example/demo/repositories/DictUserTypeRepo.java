package com.example.demo.repositories;


import com.example.demo.entities.DictUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictUserTypeRepo extends JpaRepository<DictUserType, Long> {



}
