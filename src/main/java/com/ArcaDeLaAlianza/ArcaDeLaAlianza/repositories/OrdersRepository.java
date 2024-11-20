package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String> {
    List<Orders> getOrdersByStatus(String status);

}
