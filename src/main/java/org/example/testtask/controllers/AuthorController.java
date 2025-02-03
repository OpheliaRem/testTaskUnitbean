package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorDTO;
import org.example.testtask.model.Author;
import org.example.testtask.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService service;

    @PostMapping("/create")
    public AuthorDTO createAuthor(@RequestBody Author author) {
        return service.createAuthor(author);
    }

    @GetMapping("/findAll")
    public List<AuthorDTO> getAllAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/find/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return service.getAuthor(id);
    }

    @GetMapping("/findMostPopularAuthor")
    public AuthorDTO getMostPopularAuthor(
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime) {
        return service.getMostPopularAuthor(startDateTime, endDateTime);
    }


}
