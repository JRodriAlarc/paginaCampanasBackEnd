package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.OrderStatus;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.ProductOrders;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.ProductOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/productOrders")
public class ProductOrdersController {

    @Autowired
    private ProductOrdersService productOrdersService;

    // Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<ProductOrders> createOrder(@RequestBody ProductOrders order) {
        ProductOrders createdOrder = productOrdersService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);  // Devolver el pedido creado con estado 201
    }

    // Obtener un pedido por ID
    @GetMapping("/{orderId}")
    public ResponseEntity<ProductOrders> getOrderById(@PathVariable String orderId) {
        try {
            ProductOrders order = productOrdersService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }

    // Cambiar el estado del pedido
    @PutMapping("/{orderId}/status")
    public ResponseEntity<ProductOrders> updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam OrderStatus newStatus) {
        try {
            productOrdersService.updateOrderStatus(orderId, newStatus);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Indica que la solicitud se procesó correctamente sin devolver cuerpo
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si el pedido no se encuentra
        }
    }

    // Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<ProductOrders>> getAllOrders() {
        List<ProductOrders> orders = productOrdersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
