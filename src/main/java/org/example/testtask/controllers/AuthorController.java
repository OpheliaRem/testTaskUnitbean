package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Author;
import org.springframework.web.bind.annotation.*;
import org.example.testtask.services.AuthorService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService service;

    @PostMapping("/create")
    public void createAuthor(@RequestBody Author author) {
        service.createAuthor(author);
    }

    @GetMapping("/findAll")
    public List<Author> getAllAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/find/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return service.getAuthor(id);
    }

    @PutMapping("/update")
    public void updateAuthor(@RequestBody Author author) {
        service.updateAuthor(author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
    }

    @GetMapping("/findMostPopularAuthor")
    public Author getMostPopularAuthor(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return service.getMostPopularAuthor(startDate, endDate);
    }


}
