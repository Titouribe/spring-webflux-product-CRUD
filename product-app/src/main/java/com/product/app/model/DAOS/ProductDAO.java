package com.product.app.model.DAOS;

import com.product.app.model.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductDAO extends ReactiveMongoRepository<Product, String> {
    Mono<Product> findByName(String name);
}
