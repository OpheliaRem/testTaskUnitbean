package org.example.testtask.services;

import org.example.testtask.model.Book;

import java.util.List;

public interface BookService {

    void createBook(Book book);

    List<Book> getAllBooks();

    Book getBook(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);

}
