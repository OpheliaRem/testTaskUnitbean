package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorDTO;
import org.example.testtask.model.Author;
import org.example.testtask.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository repository;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author(
                authorDTO.getId(),
                authorDTO.getFirstName(),
                authorDTO.getLastName(),
                authorDTO.getDateOfBirth()
        );

        return new AuthorDTO(repository.save(author));
    }

    public List<AuthorDTO> getAllAuthors() {
        var authors = repository.findAll();
        return authors.stream().map(AuthorDTO::new).toList();
    }

    public AuthorDTO getAuthor(Long id) {
        var author = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "author not found")
        );

        return new AuthorDTO(author);
    }

    public AuthorDTO getMostPopularAuthor(
            LocalDate startDate,
            LocalDate endDate
    ) {

        var author = repository.getAuthorByQuantityOfTakeTransactions(
                startDate,
                endDate
        );

        return new AuthorDTO(author);
    }
}
