package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.BookDto;
import org.example.testtask.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    @PostMapping("/create")
    public BookDto createBook(@RequestBody BookDto bookDTO) {
        return service.createBook(bookDTO);
    }

    @GetMapping("/findAll")
    public List<BookDto> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/find/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

}
