package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorshipDTO;
import org.example.testtask.model.Authorship;
import org.example.testtask.services.AuthorshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorshipUnits")
public class AuthorshipController {

    private AuthorshipService service;

    @PostMapping("/create")
    public AuthorshipDTO createAuthorship(@RequestBody Authorship authorship) {
        return service.createAuthorship(authorship);
    }

    @GetMapping("/findAll")
    public List<AuthorshipDTO> getAllAuthorshipUnits() {
        return service.getAllAuthorshipUnits();
    }

    @GetMapping("/find/{id}")
    public AuthorshipDTO getAuthorship(@PathVariable Long id) {
        return service.getAuthorship(id);
    }

}
