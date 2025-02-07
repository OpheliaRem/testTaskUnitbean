package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.BookDTO;
import org.example.testtask.model.Book;
import org.example.testtask.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository repository;

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getDateOfPublishing()
        );

        return new BookDTO(repository.save(book));
    }

    public List<BookDTO> getAllBooks() {
        var books = repository.findAll();

        return books.stream().map(BookDTO::new).toList();
    }

    public BookDTO getBook(Long id) {
        var book = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found")
        );

        return new BookDTO(book);
    }
}
