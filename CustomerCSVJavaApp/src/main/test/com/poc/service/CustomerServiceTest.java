package com.poc.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.poc.dto.CustomerDto;
import com.poc.entity.Customer;
import com.poc.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final String PATH_CUSTOMER_CSV = "src/test/resources/customer.csv";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;
    private CustomerDto customerDto;

    @BeforeEach
    public void setup() {
        customerDto = CustomerDto.builder()
                .customerName("John")
                .customerReference("1111")
                .addressLine1("address1")
                .addressLine2("address2")
                .town("town")
                .country("country")
                .postCode("postcode")
                .build();
    }

    @Test
    public void shouldAddCustomerAndSaveInDatabase() throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(PATH_CUSTOMER_CSV));
        customerService.addCustomer(customerDto);

        verify(customerRepository).save(any(Customer.class));
    }

}