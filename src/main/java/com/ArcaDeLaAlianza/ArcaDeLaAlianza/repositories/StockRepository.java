package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories;
import org.springframework.stereotype.Repository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    // metodo productos mayores a 0
    List<Stock> findByStockGreaterThan(int stock);
}

