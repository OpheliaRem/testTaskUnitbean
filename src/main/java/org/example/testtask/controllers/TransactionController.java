package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.TransactionDto;
import org.example.testtask.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService service;

    @PostMapping("/create")
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDTO) {
        return service.createTransaction(transactionDTO);
    }

    @GetMapping("/findAll")
    public List<TransactionDto> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/find/{id}")
    public TransactionDto getTransaction(@PathVariable Long id) {
        return service.getTransaction(id);
    }
}
