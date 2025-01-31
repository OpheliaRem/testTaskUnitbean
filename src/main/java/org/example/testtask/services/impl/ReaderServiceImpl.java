package org.example.testtask.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Reader;
import org.example.testtask.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.testtask.repositories.ReaderRepository;
import org.example.testtask.services.ReaderService;
import org.springframework.web.server.ResponseStatusException;

import org.example.testtask.dtos.DTOMostReadingReaderAndNumberOfTakenBooks;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository repository;
    BookRepository bookRepository;

    @Override
    public void createReader(Reader reader) {
        repository.save(reader);
    }

    @Override
    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    @Override
    public Reader getReader(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateReader(Reader reader) {
        repository.save(reader);
    }

    @Override
    public void deleteReader(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DTOMostReadingReaderAndNumberOfTakenBooks getReaderWithMajorityOfTakenBooks() {
        //return repository.findReaderWithMajorityOfTakenBooks();
        var reader = repository.findReaderWithMajorityOfTakenBooks();

        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found");
        }

        var quantityOfBooksTakenByReader =
                bookRepository.countBooksByReaderId(reader.getId());

        return new DTOMostReadingReaderAndNumberOfTakenBooks(reader, quantityOfBooksTakenByReader);
    }

    @Override
    public List<Reader> getReadersSortedByUnreturnedBooks() {
        return repository.findAllReadersSortedByUnreturnedBooks();
    }

}
