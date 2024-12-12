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
public class ProductImg {
    @Id
    private String id;
    @NotBlank(message = "Material es requerido")
    private String material;
    @NotNull
    @Min(value = 0, message = "el stock no puede ser menor que 0")
    private int stock;
    @NotNull
    @Min(value = 0, message = "el precio no debe ser negativo")
    private double precio;
    @NotBlank(message = "Tamaño es requerido")
    private String tamanio;
    private String kilataje;
     private String acabado;

    private Image image;
}
