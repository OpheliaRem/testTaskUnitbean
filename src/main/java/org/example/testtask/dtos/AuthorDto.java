package org.example.testtask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.model.Author;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AuthorDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    public AuthorDto(Author author) {
        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
        dateOfBirth = author.getDateOfBirth();
    }

}
