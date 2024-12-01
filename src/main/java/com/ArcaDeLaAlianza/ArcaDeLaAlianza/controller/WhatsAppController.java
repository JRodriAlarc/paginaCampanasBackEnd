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
        String phoneNumber = "527711980579";
        String message = request.getMessage();

        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String whatsappLink = "https://api.whatsapp.com/send/?phone=" + phoneNumber + "&text=" + encodedMessage;

        return ResponseEntity.ok(whatsappLink);
    }

    @PostMapping("/sendCart")
    public ResponseEntity<String> sendCartDetails(@RequestBody CarritoWhatsAppDTO request) {
        String phoneNumber = request.getPhoneNumber();
        List<CartItem> cartItems = request.getCartItems();
        Double total = request.getTotal();


        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("¡Hola!, Aquí los productos en el carrito:\n\n");
        for (CartItem item : cartItems) {
            messageBuilder.append("• ").append(item.getName())
                    .append(" (Cantidad: ").append(item.getQuantity())
                    .append(", Precio: $").append(item.getPrice())
                    .append(")\n");
        }
        messageBuilder.append("\nTotal: $").append(total);


        String encodedMessage = URLEncoder.encode(messageBuilder.toString(), StandardCharsets.UTF_8);
        String whatsappLink = "https://api.whatsapp.com/send/?phone=" + phoneNumber + "&text=" + encodedMessage;

        return ResponseEntity.ok(whatsappLink);
    }
}
