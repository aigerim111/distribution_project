package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProducts;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(Long id);
    Optional<Order> findByShopUserId(User id);
    Optional<Order> findByShopOrgId(Organization id);
    List<Order> findAllByStatus(Long status, Pageable pageable);

    //доделать запросы
    List<Order> findAllByDateOfDeliveryOrDateOfOrderRegistration(LocalDateTime dateOfDelivery, LocalDateTime dateOfOrderRegistration);
    List<Order> findAllByTotalPrice(Long totalPrice);

    Optional<Order> findByOrderProducts(OrderProducts orderProducts);

    //??
    //Set<OrderProducts> findAllBy

}
