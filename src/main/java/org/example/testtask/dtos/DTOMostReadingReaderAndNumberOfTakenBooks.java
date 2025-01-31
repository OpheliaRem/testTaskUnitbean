package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Reader;

import java.time.LocalDate;

@Data
public class DTOMostReadingReaderAndNumberOfTakenBooks {

    Long id;
    String firstName;
    String lastName;
    String gender;
    LocalDate dateOfBirth;
    String phoneNumber;

    Long numberOfBooksTaken;

    public DTOMostReadingReaderAndNumberOfTakenBooks(Reader reader, Long numberOfBooksTaken) {
        id = reader.getId();
        firstName = reader.getFirstName();
        lastName = reader.getLastName();
        gender = reader.getGender();
        dateOfBirth = reader.getDateOfBirth();
        phoneNumber = reader.getPhoneNumber();
        this.numberOfBooksTaken = numberOfBooksTaken;
    }

}
