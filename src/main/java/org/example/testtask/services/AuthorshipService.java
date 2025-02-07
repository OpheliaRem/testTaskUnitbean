package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.AuthorshipDTO;
import org.example.testtask.model.Author;
import org.example.testtask.model.Authorship;
import org.example.testtask.model.Book;
import org.example.testtask.repositories.AuthorshipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorshipService {

    AuthorshipRepository repository;

    public AuthorshipDTO createAuthorship(AuthorshipDTO authorshipDTO) {
        Author author = new Author(
                authorshipDTO.getAuthorDTO().getId(),
                authorshipDTO.getAuthorDTO().getFirstName(),
                authorshipDTO.getAuthorDTO().getLastName(),
                authorshipDTO.getAuthorDTO().getDateOfBirth()
        );

        Book book = new Book(
                authorshipDTO.getBookDTO().getId(),
                authorshipDTO.getBookDTO().getTitle(),
                authorshipDTO.getBookDTO().getDateOfPublishing()
        );

        Authorship authorship = new Authorship(
                authorshipDTO.getId(),
                author,
                book
        );

        return new AuthorshipDTO(repository.save(authorship));
    }

    public List<AuthorshipDTO> getAllAuthorshipUnits() {
        var authorshipUnits = repository.findAll();

        return authorshipUnits.stream().map(AuthorshipDTO::new).toList();
    }

    public AuthorshipDTO getAuthorship(Long id) {

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

        return new AuthorshipDTO(authorship);
    }
}
