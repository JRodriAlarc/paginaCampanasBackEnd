package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "BellFinishes")
public class BellFinish {
    @Id
    private String id;
    @NotNull(message = "debe especificar el acabado")
    @NotBlank(message = "debe especificar el acabado")
    private String finish;
    @NotNull(message = "debe especificar la descripción")
    @NotBlank(message = "debe especificar la descripción")
    private String description;

    private List<String> images;

    public BellFinish(String id, String finish, String description, List<String> images) {
        this.id = id;
        this.finish = finish;
        this.description = description;
        this.images = images;
    }

}
