package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.TransactionDTO;
import org.example.testtask.model.Transaction;
import org.example.testtask.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService service;

    @PostMapping("/create")
    public TransactionDTO createTransaction(@RequestBody Transaction transaction) {
        return service.createTransaction(transaction);
    }

    @GetMapping("/findAll")
    public List<TransactionDTO> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/find/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return service.getTransaction(id);
    }
}
