package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.ProductOrders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrdersRepository extends MongoRepository<ProductOrders, String> {
}
