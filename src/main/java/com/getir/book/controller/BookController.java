package com.getir.book.controller;


import com.getir.book.model.Book;
import com.getir.book.rest.model.BookMapper;
import com.getir.book.rest.model.request.BookDetailRequest;
import com.getir.book.rest.model.request.BookRequest;
import com.getir.book.rest.model.response.BookResponse;
import com.getir.book.service.BookService;
import com.getir.book.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<RestResponse<BookResponse>> saveBook(@Validated @RequestBody BookRequest bookRequest) {

        final Book book = bookService.saveBook(BookMapper.INSTANCE.convert(bookRequest));
        final BookResponse bookResponse = BookMapper.INSTANCE.convert(book);
        return ResponseEntity.ok(RestResponse.of(bookResponse));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<RestResponse<BookResponse>> updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody BookDetailRequest bookDetailRequest) {

        final Book book = bookService.updateBook(bookId, bookDetailRequest);
        final BookResponse bookResponse = BookMapper.INSTANCE.convert(book);
        return ResponseEntity.ok(RestResponse.of(bookResponse));
    }

}
