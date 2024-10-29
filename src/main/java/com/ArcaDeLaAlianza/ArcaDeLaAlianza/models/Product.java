package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    @NotNull
    @Indexed(unique = true)
    @NotBlank(message = "Nombre es requerido")
    private String name;
    @NotNull
    @NotBlank(message = "Description es requerido")
    private String description;
    private List<String> images =  new ArrayList<>();
    public Product(String id, String name, String description, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.images = images;
    }

}
