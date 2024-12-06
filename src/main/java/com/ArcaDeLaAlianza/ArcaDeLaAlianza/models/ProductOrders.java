package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;


import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductOrders {
    @Id
    private String orderId;
    private Customer customer;
    private List<OrderItem> products;
    private LocalDateTime orderDate = LocalDateTime.now();
    private OrderStatus status = OrderStatus.NUEVO;
    private double totalPrice;

}
