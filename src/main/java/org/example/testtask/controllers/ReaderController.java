package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.ReaderDto;
import org.example.testtask.services.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/readers")
public class ReaderController {

    private ReaderService service;

    @PostMapping("/create")
    public ReaderDto createReader(@RequestBody ReaderDto readerDTO) {
        return service.createReader(readerDTO);
    }

    @GetMapping("/findAll")
    public List<ReaderDto> getAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping("/find/{id}")
    public ReaderDto getReader(@PathVariable Long id) {
        return service.getReader(id);
    }

    @GetMapping("/findMostReadingPerson")
    public ReaderDto getMostReadingReader() {
        return service.getReaderWithMajorityOfTakenBooks();
    }

    @GetMapping("/findAllReadersSortedByUnreturnedBooks")
    public List<ReaderDto> getReadersSortedByUnreturnedBooks() {
        return service.getReadersSortedByUnreturnedBooks();
    }

}
