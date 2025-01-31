package org.example.testtask.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.ReaderAndNumberOfUnreturnedBooksDTO;
import org.example.testtask.model.Reader;
import org.example.testtask.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.testtask.repositories.ReaderRepository;
import org.example.testtask.services.ReaderService;
import org.springframework.web.server.ResponseStatusException;

import org.example.testtask.dtos.MostReadingReaderAndNumberOfTakenBooksDTO;

import java.util.ArrayList;
import java.util.HashMap;
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
    public MostReadingReaderAndNumberOfTakenBooksDTO getReaderWithMajorityOfTakenBooks() {
        var reader = repository.findReaderWithMajorityOfTakenBooks();

        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found");
        }

        var quantityOfBooksTakenByReader =
                bookRepository.countTakenBooksByReaderId(reader.getId());

        return new MostReadingReaderAndNumberOfTakenBooksDTO(reader, quantityOfBooksTakenByReader);
    }

    @Override
    public List<ReaderAndNumberOfUnreturnedBooksDTO> getReadersSortedByUnreturnedBooks() {
        var readers = repository.findAllReadersSortedByUnreturnedBooks();

        var numbersOfTakenBooks = new HashMap<Long, Long>();
        var numbersOfReturnedBooks = new HashMap<Long, Long>();

        readers.forEach(
                (reader) -> {
                    numbersOfTakenBooks.put(
                            reader.getId(),
                            bookRepository.countTakenBooksByReaderId(reader.getId()));

                    numbersOfReturnedBooks.put(
                            reader.getId(),
                            bookRepository.countReturnedBooksByReaderId(reader.getId())
                    );
                }
        );

        var numbersOfUnreturnedBooks = new HashMap<Long, Long>();

        readers.forEach(
                (reader) ->
                        numbersOfUnreturnedBooks.put(
                                reader.getId(),
                                numbersOfTakenBooks.get(reader.getId()) -
                                numbersOfReturnedBooks.get(reader.getId())
                        )
        );

        var readersAndUnreturnedBooks =
                new ArrayList<ReaderAndNumberOfUnreturnedBooksDTO>();

        readers.forEach(
                (reader) -> readersAndUnreturnedBooks.add(
                        new ReaderAndNumberOfUnreturnedBooksDTO(
                                reader,
                                numbersOfUnreturnedBooks.get(reader.getId())
                        )
                )
        );

        return readersAndUnreturnedBooks;
    }

}
