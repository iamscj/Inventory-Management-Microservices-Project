package com.iamscjoshi.microservices.product.service;

import com.iamscjoshi.microservices.product.dto.ProductRequest;
import com.iamscjoshi.microservices.product.dto.ProductResponse;
import com.iamscjoshi.microservices.product.model.Product;
import com.iamscjoshi.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price().doubleValue())
                .build();
        productRepository.save(product);

        log.info("Product created: {}, Service Layer", product);

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                BigDecimal.valueOf(product.getPrice())
        );
    }

    public List<ProductResponse> getAllProducts() {

        log.info("Product list all");

        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        BigDecimal.valueOf(product.getPrice())
                ))
                .toList();
    }
}
