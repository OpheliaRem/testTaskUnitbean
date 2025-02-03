package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.TransactionDTO;
import org.example.testtask.model.Transaction;
import org.example.testtask.model.TransactionType.TransactionType;
import org.example.testtask.repositories.BookRepository;
import org.example.testtask.repositories.ReaderRepository;
import org.example.testtask.repositories.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionService {

    TransactionRepository repository;
    BookRepository bookRepository;
    ReaderRepository readerRepository;

    public TransactionDTO createTransaction(Transaction transaction) {

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

        if (lastTransaction == null ||
                        Objects.equals(lastTransaction.getType(), TransactionType.RETURN)
        ) {
            transaction.setType(TransactionType.TAKE);
            return new TransactionDTO(repository.save(transaction));
        }

        if (readerId.equals(lastTransaction.getReader().getId())) {
            transaction.setType(TransactionType.RETURN);
            return new TransactionDTO(repository.save(transaction));
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Impossible to take book already taken by someone"
        );
    }

    public List<TransactionDTO> getAllTransactions() {
        var transactions = repository.findAll();
        return transactions.stream().map(TransactionDTO::new).toList();
    }

    public TransactionDTO getTransaction(Long id) {
        var transaction = repository.findById(id).orElse(null);

        if (transaction == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found");
        }

        return new TransactionDTO(transaction);
    }
}
