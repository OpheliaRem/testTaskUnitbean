package org.example.testtask.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Transaction;
import org.example.testtask.repositories.BookRepository;
import org.example.testtask.repositories.ReaderRepository;
import org.example.testtask.repositories.TransactionRepository;
import org.example.testtask.services.TransactionService;
import org.springframework.stereotype.Service;

import javax.management.InvalidApplicationException;
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
    public void createTransaction(Transaction transaction) throws Exception {

        var readerId = transaction.getReader().getId();
        var bookId = transaction.getBook().getId();

        var reader = readerRepository.findById(readerId);
        var book = bookRepository.findById(bookId);

        if (reader.isEmpty()) {
            throw new Exception("No such reader found");
        }

        if (book.isEmpty()) {
            throw new Exception("No such book found");
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

        throw new InvalidApplicationException(
                "Impossible to return a book taken by another reader"
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
