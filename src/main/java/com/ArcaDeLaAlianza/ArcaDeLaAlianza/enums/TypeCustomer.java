package com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums;

public enum TypeCustomer {
    REAL("cliente real"), POTENTIAL("cliente potencial");

    private String description;

    TypeCustomer(String description){
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
}
