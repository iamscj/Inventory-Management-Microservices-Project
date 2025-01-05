package com.iamscjoshi.microservices.order.request;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        int quantity
) {}