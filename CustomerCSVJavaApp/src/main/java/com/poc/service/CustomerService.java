package com.poc.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.poc.dto.CustomerDto;
import com.poc.entity.Customer;
import com.poc.repository.CustomerRepository;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(CustomerDto customerDto) {

        String csvFile = "src/main/resources/customer.csv";
        String row;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            //skip the header line
            br.readLine();
            
            while ((row =  br.readLine()) != null) {
                String[] data = row.split(csvSplitBy);
                customerDto = customerDto.toBuilder().customerReference(data[0])
                        .customerName(data[1])
                        .addressLine1(data[2])
                        .addressLine2(data[3])
                        .town(data[4])
                        .country(data[5])
                        .postCode(data[6])
                        .build();
                customerRepository.save(Customer.builder()
                        .customerName(customerDto.getCustomerName())
                        .customerReference(customerDto.getCustomerReference())
                        .addressLine1(customerDto.getAddressLine1())
                        .addressLine2(customerDto.getAddressLine2())
                        .town(customerDto.getTown())
                        .country(customerDto.getCountry())
                        .postCode(customerDto.getPostCode())
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
