package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.WhatsAppDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    @PostMapping("/send")
    public ResponseEntity<String> generateWhatsAppLink(@RequestBody WhatsAppDTO request) {
        String phoneNumber = "527711980579"; // Número fijo o recibido del formulario
        String message = request.getMessage(); // Mensaje desde el formulario

        // Codificar mensaje para URL
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String whatsappLink = "https://api.whatsapp.com/send/?phone=" + phoneNumber + "&text=" + encodedMessage;

        return ResponseEntity.ok(whatsappLink);
    }
}
