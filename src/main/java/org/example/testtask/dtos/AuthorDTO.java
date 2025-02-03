package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Author;

import java.time.LocalDate;

@Data
public class AuthorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    public AuthorDTO(Author author) {
        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
        dateOfBirth = author.getDateOfBirth();
    }

}
