package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

@Setter
@Getter
@Document(collection = "BellAlloys")
public class BellAlloy {
    @Id
    private String id;
    @NotNull
    @NotBlank(message = "debe especificar el tipo de aleación")
    private String type;
    @NotNull
    @Positive(message = "El precio por kilogramo debe ser mayor a 0")
    private double pricePerKg;

    public BellAlloy(String id, String type, double pricePerKg) {
        this.id = id;
        this.type = type;
        this.pricePerKg = pricePerKg;
    }

}


