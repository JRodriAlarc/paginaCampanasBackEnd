package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.CartItem;

import java.util.List;

public class CarritoWhatsAppDTO {
    private String phoneNumber; // Número al que se enviará el mensaje
    private List<CartItem> cartItems; // Lista de productos en el carrito
    private Double total; // Total del carrito

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
