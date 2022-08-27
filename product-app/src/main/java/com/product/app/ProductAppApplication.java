package com.product.app;

import com.product.app.model.DAOS.ProductDAO;
import com.product.app.model.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class ProductAppApplication implements CommandLineRunner {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection("products").subscribe();

        Flux.just(new Product("Iphone 13", BigDecimal.valueOf(299.99)),
                new Product("TV Xiaomi 5", BigDecimal.valueOf(99.00)),
                new Product("KTM Duke 390", BigDecimal.valueOf(1999.99)),
                new Product("Samsung galaxy s11", BigDecimal.valueOf(159.99)),
                new Product("Lenovo notebook 3", BigDecimal.valueOf(105.00)))
                .flatMap(product -> {
                    product.setDateCreated(new Date());
                    return productDAO.save(product);
                })
                .subscribe();
    }
}
