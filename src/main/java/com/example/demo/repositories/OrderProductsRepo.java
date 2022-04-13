package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProducts;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductsRepo extends JpaRepository<OrderProducts, Long> {

    Optional<OrderProducts> findAllByOrder_products_id(Long id);
    Optional<OrderProducts> findAllByOrder(Order order);
    List<OrderProducts> findAllBySuppOrgId(Organization id);
    List<OrderProducts> findAllBySuppUserId(User id);


}
