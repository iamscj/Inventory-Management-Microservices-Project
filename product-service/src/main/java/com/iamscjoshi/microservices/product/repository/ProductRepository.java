package com.iamscjoshi.microservices.product.repository;

import com.iamscjoshi.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
