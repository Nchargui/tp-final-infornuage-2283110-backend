package com.kriNad.backend.controller;

import com.kriNad.backend.exception.CustomerNotFoundException;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Kristina
@RestController
@RequestMapping("/api/Customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/listCustomers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
