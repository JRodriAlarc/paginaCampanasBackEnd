package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Orders;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // Crear una nueva orden
    @PostMapping
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders order) {
        System.out.println(order);
        Orders savedOrder = ordersService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Obtener una orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable String id) {
         return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    // Obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

//    // Actualizar una orden existente
//    @PutMapping("/{id}")
//    public ResponseEntity<Orders> updateOrder(@PathVariable String id, @Valid @RequestBody Orders updatedOrder) {
//        try {
//            Orders order = ordersService.updateOrder(id, updatedOrder);
//            return ResponseEntity.ok(order);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    // Eliminar una orden por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        try {
            ordersService.deleteOrder(id);
            return ResponseEntity.ok("Order with ID " + id + " has been deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Obtener órdenes por estado
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Orders>> getOrdersByStatus(@PathVariable String status) {
        List<Orders> orders = ordersService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
}
