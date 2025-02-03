package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.ReaderDTO;
import org.example.testtask.model.Reader;
import org.example.testtask.repositories.ReaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    ReaderRepository repository;

    public ReaderDTO createReader(Reader reader) {
        return new ReaderDTO(repository.save(reader));
    }

    public List<ReaderDTO> getAllReaders() {
        var readers = repository.findAll();

        return readers.stream().map(ReaderDTO::new).toList();
    }

    public ReaderDTO getReader(Long id) {
        var reader = repository.findById(id).orElse(null);

        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "reader not found");
        }

        return new ReaderDTO(reader);
    }

    public ReaderDTO getReaderWithMajorityOfTakenBooks() {

        var reader = repository.findReaderWithMajorityOfTakenBooks();

        return new ReaderDTO(reader);
    }

    public List<ReaderDTO> getReadersSortedByUnreturnedBooks() {
        List<Reader> readers = repository.findAllReadersSortedByUnreturnedBooks();

        return readers.stream().map(ReaderDTO::new).toList();
    }

}
