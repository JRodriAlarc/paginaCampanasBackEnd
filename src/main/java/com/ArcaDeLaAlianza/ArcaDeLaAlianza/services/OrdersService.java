package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Orders;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    // Crear una nueva orden
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // Obtener una orden por ID
    public Orders getOrderById(String orderId) {
        return ordersRepository.findById(orderId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrrado con id: " + orderId)
        );
    }

    // Obtener todas las órdenes
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

//    // Actualizar una orden existente
//    public Orders updateOrder(String orderId, Orders updatedOrder) {
//        // Primero buscar si la orden existe
//        return ordersRepository.findById(orderId).map(order -> {
//            // Actualizamos los campos con los valores del objeto actualizado
//            order.setCustomer(updatedOrder.getCustomer());
//            order.setProductType(updatedOrder.getProductType());
//            order.setAlloy(updatedOrder.getAlloy());
//            order.setFinish(updatedOrder.getFinish());
//            order.setWeightSize(updatedOrder.getWeightSize());
//            order.setQuantity(updatedOrder.getQuantity());
//            order.setStatus(updatedOrder.getStatus());
//            order.setTotalPrice(updatedOrder.getTotalPrice());
//            order.setNotes(updatedOrder.getNotes());
//            return ordersRepository.save(order);
//        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrrado con id: " + orderId));
//    }

    // Eliminar una orden por ID
    public void deleteOrder(String orderId) {
        if (ordersRepository.existsById(orderId)) {
            ordersRepository.deleteById(orderId);
        } else {
           throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrrado con id: " + orderId);
        }
    }


    public List<Orders> getOrdersByStatus(String status) {
        List<Orders> orders = ordersRepository.getOrdersByStatus(status);

        if (orders.isEmpty()) {
           throw  new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "no se encontraron ordenes con el estado: " + status);
        }
        return orders;
    }
}
