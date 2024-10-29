package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.StockProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockProductRepository extends MongoRepository<StockProduct, String> {
}
