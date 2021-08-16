package com.getir.book.controller;


import com.getir.book.dao.BookRepository;
import com.getir.book.impl.extension.exception.ResourceNotFoundException;
import com.getir.book.model.Book;
import com.getir.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@Slf4j
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        final Book newBook = bookService.createBook(book);
        return ResponseEntity.ok(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) {

        final Book book = bookService.updateBook(bookId, bookDetails);
        return ResponseEntity.ok(book);
    }

}
