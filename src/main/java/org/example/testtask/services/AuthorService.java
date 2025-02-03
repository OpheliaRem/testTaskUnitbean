package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorDTO;
import org.example.testtask.model.Author;
import org.example.testtask.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository repository;

    public AuthorDTO createAuthor(Author author) {
        return new AuthorDTO(repository.save(author));
    }

    public List<AuthorDTO> getAllAuthors() {
        var authors = repository.findAll();
        return authors.stream().map(AuthorDTO::new).toList();
    }

    public AuthorDTO getAuthor(Long id) {
        var author = repository.findById(id).orElse(null);

        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "author not found");
        }

        return new AuthorDTO(author);
    }

    public AuthorDTO getMostPopularAuthor(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    ) {

        var author = repository.getAuthorByQuantityOfTakeTransactions(
                startDateTime,
                endDateTime
        );

        return new AuthorDTO(author);
    }
}
