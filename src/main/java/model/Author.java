package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Author {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

}
