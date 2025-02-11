package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorDto;
import org.example.testtask.model.Author;
import org.example.testtask.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    AuthorRepository repository;

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author(
                authorDto.getId(),
                authorDto.getFirstName(),
                authorDto.getLastName(),
                authorDto.getDateOfBirth()
        );

        return new AuthorDto(repository.save(author));
    }

    public List<AuthorDto> getAllAuthors() {
        var authors = repository.findAll();
        return authors.stream().map(AuthorDto::new).toList();
    }

    public AuthorDto getAuthor(Long id) {
        var author = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "author not found")
        );

        return new AuthorDto(author);
    }

    public AuthorDto getMostPopularAuthor(
            LocalDate startDate,
            LocalDate endDate
    ) {

        var author = repository.getAuthorByQuantityOfTakeTransactions(
                startDate,
                endDate
        );

        return new AuthorDto(author);
    }
}
