package com.iamscjoshi.microservices.order.service;

import com.iamscjoshi.microservices.order.model.Order;
import com.iamscjoshi.microservices.order.repository.OrderRepository;
import com.iamscjoshi.microservices.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(orderRequest.orderNumber())
                .skuCode(orderRequest.skuCode())
                .id(orderRequest.id())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();

        orderRepository.save(order);

        log.info("Placed order: {}", order);
    }
}
