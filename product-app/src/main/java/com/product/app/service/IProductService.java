package com.product.app.service;

import com.product.app.model.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<Product> saveProduct(Product product);
    Mono<Product> findByName(String name);
    Mono<String> deleteById(Product product);
}
