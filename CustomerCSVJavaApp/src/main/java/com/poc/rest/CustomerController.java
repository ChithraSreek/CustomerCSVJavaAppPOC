package com.poc.rest;

import com.poc.dto.CustomerDto;
import com.poc.entity.Customer;
import com.poc.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    public CustomerService service;

    public CustomerController(final CustomerService service){
        this.service = service;
    }

    @PostMapping(value = "/addCustomer", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addCustomer(@RequestBody final CustomerDto customerDto) {
        service.addCustomer(customerDto);
    }

    @GetMapping(value = "/getAllCustomers")
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }
}
