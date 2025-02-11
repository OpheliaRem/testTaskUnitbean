package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorshipDto;
import org.example.testtask.model.Authorship;
import org.example.testtask.repositories.AuthorRepository;
import org.example.testtask.repositories.AuthorshipRepository;
import org.example.testtask.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorshipService {

    AuthorshipRepository repository;
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public AuthorshipDto createAuthorship(AuthorshipDto authorshipDto) {

        var author = authorRepository.findById(authorshipDto.getAuthorId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "author not found")
        );

        var book = bookRepository.findById(authorshipDto.getBookId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found")
        );

        Authorship authorship = new Authorship(
                authorshipDto.getId(),
                author,
                book
        );

        return new AuthorshipDto(repository.save(authorship));
    }

    public List<AuthorshipDto> getAllAuthorshipUnits() {
        var authorshipUnits = repository.findAll();

        return authorshipUnits.stream().map(AuthorshipDto::new).toList();
    }

    public AuthorshipDto getAuthorship(Long id) {

        var authorship = repository.findById(id).orElseThrow(
                () ->  new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "authorship not found"
                )
        );

        if (authorship == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "authorship not found"
            );
        }

        return new AuthorshipDto(authorship);
    }
}
