package org.example.testtask.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Transaction;
import org.example.testtask.repositories.BookRepository;
import org.example.testtask.repositories.ReaderRepository;
import org.example.testtask.repositories.TransactionRepository;
import org.example.testtask.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository repository;
    BookRepository bookRepository;
    ReaderRepository readerRepository;

    @Override
    public void createTransaction(Transaction transaction) {

        var readerId = transaction.getReader().getId();
        var bookId = transaction.getBook().getId();

        var reader = readerRepository.findById(readerId);
        var book = bookRepository.findById(bookId);

        if (reader.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Reader not found"
            );
        }

        if (book.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found"
            );
        }

        var lastTransaction = repository.findLastTransactionWhereBookId(bookId);

        transaction.setTimeOfOperation(LocalDateTime.now());

        if (
                lastTransaction == null ||
                        Objects.equals(lastTransaction.getType(), "give back")
        ) {
            transaction.setType("take");
            repository.save(transaction);
            return;
        }

        if (readerId.equals(lastTransaction.getReader().getId())) {
            transaction.setType("give back");
            repository.save(transaction);
            return;
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Impossible to take book already taken by someone"
        );
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public Transaction getTransaction(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
}
