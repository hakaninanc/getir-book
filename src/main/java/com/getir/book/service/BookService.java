package com.getir.book.service;


import com.getir.book.dao.BookRepository;
import com.getir.book.impl.extension.exception.ResourceNotFoundException;
import com.getir.book.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Book createBook(Book book) {
        try {
            book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
            bookRepository.save(book);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getMessage());
        }
        log.info("Create successfull");
        return book;
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        try {
            final Book _book = bookRepository.findById(bookId).
                    orElseThrow(() -> new ResourceNotFoundException("Book is not found for this id :: " + bookId));

            _book.setName(bookDetails.getName());
            _book.setAuthor(bookDetails.getAuthor());
            _book.setStock(bookDetails.getStock());

            final Book updatedBook = bookRepository.save(_book);
            return updatedBook;
        } catch (Exception e) {
            return null;
        }
    }

}
