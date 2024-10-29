package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import java.util.List;

public class StockProduct extends Product{
    private int stock;
    private double price;

    public StockProduct(String id, String name, String description, List<String> images, int stock, int price) {
        super(id, name, description, images);
        this.stock= stock;
        this.price = price;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
