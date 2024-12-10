package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.CartItem;

import java.util.List;

public class CarritoWhatsAppDTO {
    private String phoneNumber;
    private List<CartItem> cartItems;
    private Double total;

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