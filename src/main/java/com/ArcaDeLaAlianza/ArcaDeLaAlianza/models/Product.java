package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    @NotBlank(message = "Nombre es requerido")
    private String name;
    @NotNull
    @Min(value = 0, message = "el stock no puede ser menor que 0")
    private int stock;
    @NotNull
    @Min(value = 0, message = "el precio no debe ser negativo")
    private double price;
    @NotBlank(message = "Description es requerido")
    private String description;
    private Image image;
}
