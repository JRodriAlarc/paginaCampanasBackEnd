package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
    private Role role;

}
