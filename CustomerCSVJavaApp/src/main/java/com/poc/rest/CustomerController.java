package com.poc.rest;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.poc.dto.CustomerDto;
import com.poc.entity.Customer;
import com.poc.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    public CustomerService service;

    public CustomerController(final CustomerService service){
        this.service = service;
    }

    @PostMapping(value = "/addCustomer", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public void addCustomer(@RequestBody final CustomerDto customerDto) {
        service.addCustomer(customerDto);
    }

    @GetMapping(value = "/getAllCustomers")
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }
}
