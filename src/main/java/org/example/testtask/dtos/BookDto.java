package org.example.testtask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.model.Book;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private LocalDate dateOfPublishing;

    public BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        dateOfPublishing = book.getDateOfPublishing();
    }
}
