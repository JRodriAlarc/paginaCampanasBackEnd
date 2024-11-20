package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    private String orderId;
    private Customer customer;
    private BellAlloy alloy;
    private BellFinish finish;
    private BellWeightSize weightSize;
    private LocalDateTime orderDate = LocalDateTime.now();
    private OrderStatus status = OrderStatus.NUEVO;
    private double totalPrice;
    private String customNote;

}

