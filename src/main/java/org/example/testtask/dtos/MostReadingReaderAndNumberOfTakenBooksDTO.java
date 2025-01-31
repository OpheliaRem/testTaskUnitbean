package org.example.testtask.dtos;

import lombok.Data;
import lombok.NonNull;
import org.example.testtask.model.Reader;

@Data
public class MostReadingReaderAndNumberOfTakenBooksDTO {

    @NonNull
    Reader reader;
    @NonNull
    Long numberOfBooksTaken;

}
