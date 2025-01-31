package org.example.testtask.dtos;

import lombok.Data;
import lombok.NonNull;
import org.example.testtask.model.Reader;

@Data
public class ReaderAndNumberOfUnreturnedBooksDTO {

    @NonNull
    Reader reader;

    @NonNull
    Long numberOfUnreturnedBooks;

}
