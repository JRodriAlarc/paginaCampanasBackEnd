package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotNull(message = "el nombre no debe ser nulo")
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    @NotNull
    private Role role;
}
