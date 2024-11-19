package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    Product findByName(String name);

}
