package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config.JwtUtil;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.AuthDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.User;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {



    @Autowired
   private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?>  login(@Valid @RequestBody AuthDTO authDTO) {
        String token = authService.login(authDTO.getUsername(), authDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user) {
        User userResponse = authService.register(user);
        // Generar el token JWT utilizando JwtUtil
        String token = jwtUtil.generateToken(user.getUsername());

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "User registered successfully");
        responseBody.put("token", token); // Opcional, si quieres devolver el token en la respuesta

        return ResponseEntity.ok(responseBody);

//        return ResponseEntity.ok(authService.register(user));

    }

}
