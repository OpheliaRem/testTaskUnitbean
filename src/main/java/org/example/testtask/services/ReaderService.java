package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.ReaderDto;
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

    public ReaderDto createReader(ReaderDto readerDto) {
        Reader reader = new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getGender(),
                readerDto.getDateOfBirth(),
                readerDto.getPhoneNumber()
        );

        return new ReaderDto(repository.save(reader));
    }

    public List<ReaderDto> getAllReaders() {
        var readers = repository.findAll();

        return readers.stream().map(ReaderDto::new).toList();
    }

    public ReaderDto getReader(Long id) {
        var reader = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "reader not found")
        );

        return new ReaderDto(reader);
    }

    public ReaderDto getReaderWithMajorityOfTakenBooks() {

        var reader = repository.findReaderWithMajorityOfTakenBooks();

        return new ReaderDto(reader);
    }

    public List<ReaderDto> getReadersSortedByUnreturnedBooks() {
        List<Reader> readers = repository.findAllReadersSortedByUnreturnedBooks();

        return readers.stream().map(ReaderDto::new).toList();
    }

}
