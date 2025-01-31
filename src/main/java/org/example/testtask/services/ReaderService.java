package org.example.testtask.services;

import org.example.testtask.dtos.DTOMostReadingReaderAndNumberOfTakenBooks;
import org.example.testtask.model.Reader;

import java.util.List;

public interface ReaderService {

    void createReader(Reader reader);

    List<Reader> getAllReaders();

    Reader getReader(Long id);

    void updateReader(Reader reader);

    void deleteReader(Long id);

    DTOMostReadingReaderAndNumberOfTakenBooks getReaderWithMajorityOfTakenBooks();

    List<Reader> getReadersSortedByUnreturnedBooks();

}
