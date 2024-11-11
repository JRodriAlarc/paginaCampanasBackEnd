package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config.JwtUtil;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.AuthDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.UserInfoDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.User;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        String token = authService.login(authDTO);
//        devolver datos del usuario y el token
        UserInfoDTO userInfo = authService.getUser(authDTO.getUsername(), token);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO user) {
        User userResponse = authService.register(user);
        // Generar el token JWT utilizando JwtUtil
        String token = jwtUtil.generateToken(user.getUsername());

        UserInfoDTO userInfo = authService.getUser(user.getUsername(), token);

        return ResponseEntity.ok(userInfo);

    }


    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        UserInfoDTO userInfo = authService.getUser(username, token);
        return ResponseEntity.ok(userInfo);
    }


}
