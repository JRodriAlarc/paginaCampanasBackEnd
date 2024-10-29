package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import java.util.List;

public class CustomizableProduct extends Product {
    private List<String> customizableOptions;
    public CustomizableProduct(String id, String name, String description, List<String> images) {
        super(id, name, description, images);
    }
}
