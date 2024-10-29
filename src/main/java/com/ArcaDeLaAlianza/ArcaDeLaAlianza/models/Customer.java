package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.TypeCustomer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    @NotNull
    private String name;
    private String email;
    private String phone;
    private  TypeCustomer typeCustomer;
    private String address;
    private String city;
    private String state;
    public Customer() {
    }

    public Customer(String id, String name, String phone, String email,
                    TypeCustomer typeCustomer, String address, String city, String state) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.typeCustomer = typeCustomer;
        this.address = address;
        this.city = city;
        this.state = state;
    }

}

