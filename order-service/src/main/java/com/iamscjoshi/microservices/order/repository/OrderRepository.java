package com.iamscjoshi.microservices.order.repository;

import com.iamscjoshi.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
