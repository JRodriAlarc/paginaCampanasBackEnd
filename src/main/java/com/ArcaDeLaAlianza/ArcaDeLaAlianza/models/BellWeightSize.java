package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "BellWeightSizes")
public class BellWeightSize {
    @Id
    private  String id;
    @NotNull(message = "debe especificar un peso")
    private int weight;
    @NotNull(message = "debe especificar un peso")
    private int diameter;
    @NotNull(message = "debe especificar un peso")
    private int height;

    public BellWeightSize(String id, int weight, int diameter, int height) {
        this.id = id;
        this.weight = weight;
        this.diameter = diameter;
        this.height = height;
    }

}

