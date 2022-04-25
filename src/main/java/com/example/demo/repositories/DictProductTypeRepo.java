package com.example.demo.repositories;

import com.example.demo.entities.DictProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictProductTypeRepo extends JpaRepository<DictProductType, Long> {


}
