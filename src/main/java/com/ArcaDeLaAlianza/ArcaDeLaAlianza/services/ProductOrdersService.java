package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.OrderStatus;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Customer;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Orders;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.ProductOrders;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.ProductOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductOrdersService {

    @Autowired
    private ProductOrdersRepository ordersRepository;

    // Crear un nuevo pedido
    public ProductOrders createOrder(ProductOrders order) {
        return ordersRepository.save(order);  // Guardar en MongoDB
    }


    // Obtener un pedido por ID
    public ProductOrders getOrderById(String orderId) {
        return ordersRepository.findById(orderId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrrado con id: " + orderId)
        );
    }


    // Cambiar el estado del pedido
    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        ProductOrders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        ordersRepository.save(order);  // Guardar los cambios en MongoDB
    }



    // Listar todos los pedidos
    public List<ProductOrders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
