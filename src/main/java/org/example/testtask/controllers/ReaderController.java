package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.ReaderDTO;
import org.example.testtask.services.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/readers")
public class ReaderController {

    private ReaderService service;

    @PostMapping("/create")
    public ReaderDTO createReader(@RequestBody ReaderDTO readerDTO) {
        return service.createReader(readerDTO);
    }

    @GetMapping("/findAll")
    public List<ReaderDTO> getAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping("/find/{id}")
    public ReaderDTO getReader(@PathVariable Long id) {
        return service.getReader(id);
    }

    @GetMapping("/findMostReadingPerson")
    public ReaderDTO getMostReadingReader() {
        return service.getReaderWithMajorityOfTakenBooks();
    }

    @GetMapping("/findAllReadersSortedByUnreturnedBooks")
    public List<ReaderDTO> getReadersSortedByUnreturnedBooks() {
        return service.getReadersSortedByUnreturnedBooks();
    }

}
