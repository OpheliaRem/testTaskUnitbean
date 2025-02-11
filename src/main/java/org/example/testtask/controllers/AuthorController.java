package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorDto;
import org.example.testtask.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService service;

    @PostMapping("/create")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDTO) {
        return service.createAuthor(authorDTO);
    }

    @GetMapping("/findAll")
    public List<AuthorDto> getAllAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/find/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        return service.getAuthor(id);
    }

    @GetMapping("/findMostPopularAuthor")
    public AuthorDto getMostPopularAuthor(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return service.getMostPopularAuthor(startDate, endDate);
    }


}
