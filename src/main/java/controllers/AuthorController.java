package controllers;

import lombok.AllArgsConstructor;
import model.Author;
import org.springframework.web.bind.annotation.*;
import services.AuthorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
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

}
