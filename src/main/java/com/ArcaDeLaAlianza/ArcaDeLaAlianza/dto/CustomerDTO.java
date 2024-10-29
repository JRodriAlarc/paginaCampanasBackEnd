package com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.TypeCustomer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO {
    private String name;
    private String email;
    private String phone;

    private String address;
    private String city;
    private String state;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String phone, String email,
            String address, String city, String state) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
    }

}
