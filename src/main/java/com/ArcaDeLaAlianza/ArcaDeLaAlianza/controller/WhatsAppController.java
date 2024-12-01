package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.CartItem;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.CarritoWhatsAppDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.WhatsAppDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    @PostMapping("/sendCart")
    public ResponseEntity<String> sendCartDetails(@RequestBody CarritoWhatsAppDTO request) {
        String phoneNumber = request.getPhoneNumber(); // Número dinámico
        List<CartItem> cartItems = request.getCartItems();
        Double total = request.getTotal();

        // Crear el mensaje
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("¡Hola!, Aquí los productos en el carrito:\n\n");
        for (CartItem item : cartItems) {
            messageBuilder.append("• ").append(item.getName())
                    .append(" (Cantidad: ").append(item.getQuantity())
                    .append(", Precio: $").append(item.getPrice())
                    .append(")\n");
        }
        messageBuilder.append("\nTotal: $").append(total);

        // Codificar mensaje para URL
        String encodedMessage = URLEncoder.encode(messageBuilder.toString(), StandardCharsets.UTF_8);
        String whatsappLink = "https://api.whatsapp.com/send/?phone=" + phoneNumber + "&text=" + encodedMessage;

        return ResponseEntity.ok(whatsappLink);
    }
}
