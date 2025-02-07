package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.BookDTO;
import org.example.testtask.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    @PostMapping("/create")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return service.createBook(bookDTO);
    }

    @GetMapping("/findAll")
    public List<BookDTO> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/find/{id}")
    public BookDTO getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

}
