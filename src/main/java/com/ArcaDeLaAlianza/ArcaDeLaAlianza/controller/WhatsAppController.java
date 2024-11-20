package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatsAppController {
    @GetMapping("/generate-link")
    public String generateWhatsAppLink(
            @RequestParam String phone,
            @RequestParam String message) {
        // Formatear el mensaje para que sea URL-encoded
        String formattedMessage = message.replace(" ", "%20");

        // Construir el enlace
        String link = "https://api.whatsapp.com/send?phone=" + phone + "&message=" + formattedMessage;
        return link;
    }
}