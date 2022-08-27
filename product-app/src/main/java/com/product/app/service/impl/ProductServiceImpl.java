package com.product.app.service.impl;

import com.product.app.constants.ValidationConstants;
import com.product.app.model.DAOS.ProductDAO;
import com.product.app.model.entities.Product;
import com.product.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Flux<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productDAO.findById(id);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Mono<Product> findByName(String name) {
        return productDAO.findByName(name);
    }

    @Override
    public Mono<String> deleteById(Product product) {
        productDAO.delete(product);
        return Mono.just(ValidationConstants.isDeleted(product.getClass().getSimpleName(), product.getName()));
    }
}
