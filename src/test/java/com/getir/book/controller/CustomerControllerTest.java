package com.getir.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.book.dao.CustomerRepository;
import com.getir.book.dao.OrderRepository;
import com.getir.book.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void saveCustomer() throws Exception {

        Customer customer = new Customer();
                customer.setName("John");
                customer.setSurname("Doe");
                customer.setMail("johndoe@getir.com");

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);


        mock.perform(MockMvcRequestBuilders
                .post("/api/v1/customer/save")
                .content(asJsonString(new Customer(null, "John", "Doe", "johndoe@getir.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists());
    }

//    @Test
//    void getOrders() throws Exception {
//
//        mock.perform(MockMvcRequestBuilders
//                .get("/api/v1/customer/{id}/orders",1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1));
//    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}