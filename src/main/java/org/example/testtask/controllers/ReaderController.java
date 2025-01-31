package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.MostReadingReaderAndNumberOfTakenBooksDTO;
import org.example.testtask.model.Reader;
import org.springframework.web.bind.annotation.*;
import org.example.testtask.services.ReaderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/readers")
public class ReaderController {

    private ReaderService service;

    @PostMapping("/create")
    public void createReader(@RequestBody Reader reader) {
        service.createReader(reader);
    }

    @GetMapping("/findAll")
    public List<Reader> getAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping("/find/{id}")
    public Reader getReader(@PathVariable Long id) {
        return service.getReader(id);
    }

    @PutMapping("/update")
    public void updateReader(@RequestBody Reader reader) {
        service.updateReader(reader);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReader(@PathVariable Long id) {
        service.deleteReader(id);
    }

    @GetMapping("/findMostReadingPerson")
    public MostReadingReaderAndNumberOfTakenBooksDTO getMostReadingReader() {
        return service.getReaderWithMajorityOfTakenBooks();
    }

    @GetMapping("/findAllReadersSortedByUnreturnedBooks")
    public List<Reader> getReadersSortedByUnreturnedBooks() {
        return service.getReadersSortedByUnreturnedBooks();
    }

}
