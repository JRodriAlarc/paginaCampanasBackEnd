package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String nombre;
    private Integer cantidad;
    private double precio_unitario;
}
