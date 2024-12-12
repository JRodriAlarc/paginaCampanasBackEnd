package com.ArcaDeLaAlianza.ArcaDeLaAlianza.models;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.TypeCustomer;
import jakarta.validation.constraints.NotNull;
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
    private String mailingAddress;

    public Customer() {
    }

    public Customer(String id, String name, String phone, String email,
                    TypeCustomer typeCustomer, String mailingAddress) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mailingAddress=mailingAddress;

    }
}

