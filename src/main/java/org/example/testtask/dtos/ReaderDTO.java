package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Reader;
import org.example.testtask.model.gender.Gender;

import java.time.LocalDate;

@Data
public class ReaderDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    public ReaderDTO(Reader reader) {
        id = reader.getId();
        firstName = reader.getFirstName();
        lastName = reader.getLastName();
        gender = reader.getGender();
        dateOfBirth = reader.getDateOfBirth();
        phoneNumber = reader.getPhoneNumber();
    }

}
