package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {

    private enum Gender {
        MALE,
        FEMALE
    }

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;
}
