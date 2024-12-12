package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Customer;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class customerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customerDTO){
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return ResponseEntity.ok().body("Customer updated" + customer.getId());
    }
}
