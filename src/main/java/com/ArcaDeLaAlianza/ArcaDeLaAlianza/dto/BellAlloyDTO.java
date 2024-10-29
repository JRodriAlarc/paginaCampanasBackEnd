package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BellAlloyDTO {
    @NotNull
    @NotBlank(message = "debe especificar el tipo de aleación")
    private String type;
    @NotNull
    @Positive(message = "El precio por kilogramo debe ser mayor a 0")
    private double pricePerKg;

    public BellAlloyDTO( String type, double pricePerKg) {
        this.type = type;
        this.pricePerKg = pricePerKg;
    }

}
