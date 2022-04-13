package com.example.demo.repositories;

import com.example.demo.entities.Organization;
import com.example.demo.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

    Optional<Warehouse> findByWarehouse_id(Long id);
    List<Warehouse> findAllByOrg(Organization org);
    Optional<Warehouse> findByAddressOrPhone(String address, String phone);

    //??
}
