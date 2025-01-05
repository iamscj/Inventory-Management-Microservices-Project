package com.iamscjoshi.microservices.order.service;

import com.iamscjoshi.microservices.order.model.Order;
import com.iamscjoshi.microservices.order.repository.OrderRepository;
import com.iamscjoshi.microservices.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price().multiply(BigDecimal.valueOf(orderRequest.quantity())))
                .skuCode(orderRequest.skuCode())
                .quantity(orderRequest.quantity())
                .build();

        orderRepository.save(order);

        log.info("Placed order: {}", order);
    }
}
