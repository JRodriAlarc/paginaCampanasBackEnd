package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    @NotNull(message = "el nombre no debe ser nulo")
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String phone;
    private Role role;

    private String token;

}