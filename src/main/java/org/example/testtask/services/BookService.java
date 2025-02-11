package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.BookDto;
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

    public BookDto createBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getDateOfPublishing()
        );

        return new BookDto(repository.save(book));
    }

    public List<BookDto> getAllBooks() {
        var books = repository.findAll();

        return books.stream().map(BookDto::new).toList();
    }

    public BookDto getBook(Long id) {
        var book = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found")
        );

        return new BookDto(book);
    }
}
