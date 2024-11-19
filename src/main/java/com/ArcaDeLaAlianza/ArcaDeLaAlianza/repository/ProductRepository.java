package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repository;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional; // Asegúrate de agregar esta línea


public interface ProductRepository extends MongoRepository<Product, Integer> {
    Optional<Product> findByName(String name);
}
