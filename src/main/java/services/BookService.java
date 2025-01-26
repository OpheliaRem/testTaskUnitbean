package services;

import model.Book;

import java.util.List;

public interface BookService {

    void createBook(Book book);

    List<Book> getAllBooks();

    Book getBook(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);

}
