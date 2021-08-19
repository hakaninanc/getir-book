package com.getir.book.service;


import com.getir.book.dao.BookRepository;
import com.getir.book.model.Book;
import com.getir.book.model.Order;
import com.getir.book.rest.model.BookMapper;
import com.getir.book.rest.model.request.BookDetailRequest;
import com.getir.book.rest.model.request.BookRequest;
import com.getir.book.rest.model.response.BookResponse;
import com.getir.book.util.exception.ResourceNotFoundException;
import com.getir.book.util.exception.SaveErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Book saveBook(Book book) {
        try {
            book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
            final Book newBook = bookRepository.save(book);
            log.info("Create successfull");

            return newBook;
        } catch (SaveErrorException e) {
            log.error(e.getMessage(), e.getMessage());
            return null;
        }
    }

    public Book updateBook(Long bookId, BookDetailRequest request) throws ResourceNotFoundException {
            final Book book = bookRepository.findById(bookId).
                    orElseThrow(() -> new ResourceNotFoundException("Book is not found for this id :: " + bookId));

            book.setStock(request.getStock());

            final Book updatedBook = bookRepository.save(book);
            log.info("Update successfull");

            return updatedBook;
    }

    public Book findBookById(Long bookId) {
        try {
            final Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + bookId));
            return book;
        } catch (Exception e) {
            return null;
        }
    }


    public boolean checkStock(Book orderedBook, Book availableBook) {
        if (availableBook.getStock() - orderedBook.getStock() > -1) {
            return true;
        }
        return false;
    }

    public List<Book> updateStock(List<Book> orderedBooks) {
        for (Book orderedBook : orderedBooks) {
            final Book availableBook = findBookById(orderedBook.getId());
            if (checkStock(orderedBook, availableBook)) {
                availableBook.setStock(availableBook.getStock() - orderedBook.getStock());
                try {
                    updateBook(orderedBook.getId(), BookMapper.INSTANCE.convertToBookDetail(orderedBook));
                    log.info("Stock dropped");
                } catch (Exception e) {
                    log.error(e.getMessage(), e.getMessage());
                }
            }
        }
        return orderedBooks;
    }

    public List<Book> getAvailableBooks(Order order, List<Book> demandedBooks) {
        for (Book orderedBook: order.getBooks()) {
            final Book availableBook = findBookById(orderedBook.getId());
            if (availableBook != null && checkStock(orderedBook, availableBook)) {
                availableBook.setStock(orderedBook.getStock());
                demandedBooks.add(availableBook);
            } else {
                log.info("No stock for this book. Id: " + orderedBook.getId());
            }
        }
        return demandedBooks;
    }

}
