package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {

    private String title;

    private LocalDate dateOfPublishing;

}
