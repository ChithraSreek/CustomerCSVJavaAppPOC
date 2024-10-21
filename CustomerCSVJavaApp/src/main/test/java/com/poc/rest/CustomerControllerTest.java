package com.poc.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.dto.CustomerDto;
import com.poc.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CustomerController.class})
@WebMvcTest
class CustomerControllerTest {

    private static final String ADD_CUSTOMER_URL =  "/v1/customers/addCustomer";
    private static final String GET_CUSTOMER_URL =  "/v1/customers/getAllCustomers";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerDto customerDto;
    private String json;

    @BeforeEach
    public void setup() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        customerDto = CustomerDto.builder()
                .customerName("John")
                .customerReference("1111")
                .addressLine1("address1")
                .addressLine2("address2")
                .town("town")
                .country("country")
                .postCode("postcode")
                .build();
        json = mapper.writeValueAsString(customerDto);
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        mockMvc.perform(post(ADD_CUSTOMER_URL).contentType(APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isCreated());

        verify(customerService).addCustomer(customerDto);
    }

    @Test
    void shouldGetAllCustomers() throws Exception {
        mockMvc.perform(get(GET_CUSTOMER_URL).contentType(APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk());

        verify(customerService).getAllCustomers();
    }

}