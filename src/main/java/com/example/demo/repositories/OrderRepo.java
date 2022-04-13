package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.entities.OrderProducts;
import com.example.demo.entities.Organization;
import com.example.demo.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Optional<Order> findAllByOrder_id(Long id);
    Optional<Order> findAllByShopUserId(User id);
    Optional<Order> findAllByShopOrgId(Organization id);
    List<Order> findAllByStatus(Long status, Pageable pageable);

    //доделать запросы
    List<Order> findAllByDateOfDeliveryOrDateOfOrderRegistration(LocalDateTime dateOfDelivery, LocalDateTime dateOfOrderRegistration);
    List<Order> findAllByTotalPrice(Long totalPrice);

    //??
    //Set<OrderProducts> findAllBy

}
