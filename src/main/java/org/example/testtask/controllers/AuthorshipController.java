package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorshipDto;
import org.example.testtask.services.AuthorshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorshipUnits")
public class AuthorshipController {

    private AuthorshipService service;

    @PostMapping("/create")
    public AuthorshipDto createAuthorship(@RequestBody AuthorshipDto authorshipDTO) {
        return service.createAuthorship(authorshipDTO);
    }

    @GetMapping("/findAll")
    public List<AuthorshipDto> getAllAuthorshipUnits() {
        return service.getAllAuthorshipUnits();
    }

    @GetMapping("/find/{id}")
    public AuthorshipDto getAuthorship(@PathVariable Long id) {
        return service.getAuthorship(id);
    }

}
