package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Authorship;
import org.springframework.web.bind.annotation.*;
import org.example.testtask.services.AuthorshipService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorshipUnits")
public class AuthorshipController {

    private AuthorshipService service;

    @PostMapping("/create")
    public void createAuthorship(@RequestBody Authorship authorship) {
        service.createAuthorship(authorship);
    }

    @GetMapping("/findAll")
    public List<Authorship> getAllAuthorshipUnits() {
        return service.getAllAuthorshipUnits();
    }

    @GetMapping("/find/{id}")
    public Authorship getAuthorship(@PathVariable Long id) {
        return service.getAuthorship(id);
    }

    @PutMapping("/update")
    public void updateAuthorship(@RequestBody Authorship authorship) {
        service.updateAuthorship(authorship);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthorship(@PathVariable Long id) {
        service.deleteAuthorship(id);
    }

}
