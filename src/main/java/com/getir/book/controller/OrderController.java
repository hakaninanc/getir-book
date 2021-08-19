package com.getir.book.controller;


import com.getir.book.model.Book;
import com.getir.book.model.Order;
import com.getir.book.rest.model.BookMapper;
import com.getir.book.rest.model.OrderMapper;
import com.getir.book.rest.model.request.OrderRequest;
import com.getir.book.rest.model.response.OrderResponse;
import com.getir.book.service.BookService;
import com.getir.book.service.OrderService;
import com.getir.book.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<RestResponse<OrderResponse>> saveOrder(@Validated @RequestBody OrderRequest orderRequest) {

        final List<Book> orderedBooks = BookMapper.INSTANCE.convert(orderRequest.getBooks());
        final Order order = OrderMapper.INSTANCE.convert(orderRequest);
        order.setBooks(orderedBooks);

        final Order newOrder = orderService.saveOrder(order);
        final OrderResponse orderResponse = OrderMapper.INSTANCE.convert(newOrder);

        return ResponseEntity.ok(RestResponse.of(orderResponse));
    }

    @GetMapping("/orders")
    public ResponseEntity<RestResponse<List<OrderResponse>>> getOrders() {

        final List<Order> orderList = orderService.findAllOrders();
        final List<OrderResponse> orderResponseList = OrderMapper.INSTANCE.convert(orderList);
        return ResponseEntity.ok(RestResponse.of(orderResponseList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<OrderResponse>> getOrderById(@PathVariable(value = "id") Long orderId) {

        final Order order = orderService.getOrder(orderId);
        final OrderResponse orderResponse = OrderMapper.INSTANCE.convert(order);
        return ResponseEntity.ok(RestResponse.of(orderResponse));
    }


}
