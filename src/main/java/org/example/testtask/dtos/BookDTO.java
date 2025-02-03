package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Book;

import java.time.LocalDate;

@Data
public class BookDTO {

    private Long id;

    private String title;

    private LocalDate dateOfPublishing;

    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        dateOfPublishing = book.getDateOfPublishing();
    }
}
