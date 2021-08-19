package com.getir.book.controller;

import com.getir.book.model.Customer;
import com.getir.book.model.Order;
import com.getir.book.rest.model.CustomerMapper;
import com.getir.book.rest.model.OrderMapper;
import com.getir.book.rest.model.request.CustomerRequest;
import com.getir.book.rest.model.response.CustomerResponse;
import com.getir.book.rest.model.response.OrderResponse;
import com.getir.book.service.CustomerService;
import com.getir.book.service.OrderService;
import com.getir.book.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<RestResponse<CustomerResponse>> saveCustomer(@Validated @RequestBody CustomerRequest customerRequest) {

        final Customer customer = customerService.saveCustomer(CustomerMapper.INSTANCE.convert(customerRequest));
        final CustomerResponse customerResponse = CustomerMapper.INSTANCE.convert(customer);
        return ResponseEntity.ok(RestResponse.of(customerResponse));
        
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<RestResponse<List<OrderResponse>>> getOrders(@PathVariable(value = "id") Long customerId) {

        final List<Order> orderList = orderService.getCustomersOrders(customerId);
        final List<OrderResponse> orderResponseList = OrderMapper.INSTANCE.convert(orderList);
        return ResponseEntity.ok(RestResponse.of(orderResponseList));
    }
}

