package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.CustomerDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.enums.TypeCustomer;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Customer;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
     CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(CustomerDTO customerDTO){
        TypeCustomer typeCustomer = TypeCustomer.POTENTIAL;
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setTypeCustomer(typeCustomer);

        return customerRepository.save(customer);
    }
    public void updateCustomer(Customer customer){
        Customer newCustomer =customerRepository.findById(customer.getId()).orElse(null);
        if(newCustomer != null){
            newCustomer.setName(customer.getName());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setPhone(customer.getPhone());
            customerRepository.save(newCustomer);
        }
    }





}
