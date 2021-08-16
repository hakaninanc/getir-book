package com.getir.book.controller;


import com.getir.book.dao.OrderRepository;
import com.getir.book.impl.extension.exception.ResourceNotFoundException;
import com.getir.book.model.Order;
import com.getir.book.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity <Order> getOrderById(@PathVariable(value = "id") Long orderId) {

        final Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok().body(order);
    }
}
