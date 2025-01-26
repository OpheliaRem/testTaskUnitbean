package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reader {

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;
}
