package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.TransactionDto;
import org.example.testtask.model.Transaction;
import org.example.testtask.model.transactionType.TransactionType;
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

    public TransactionDto createTransaction(TransactionDto transactionDto) {

        var readerId = transactionDto.getReaderId();
        var bookId = transactionDto.getBookId();

        var reader = readerRepository.findById(readerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "reader not found")
        );
        var book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found")
        );

        var transaction = new Transaction(
                transactionDto.getId(),
                transactionDto.getType(),
                transactionDto.getTimeOfOperation(),
                reader,
                book
        );

        var lastTransaction = repository.findLastTransactionWhereBookId(bookId);

        transaction.setTimeOfOperation(LocalDateTime.now());

        if (lastTransaction == null ||
                        Objects.equals(lastTransaction.getType(), TransactionType.RETURN)
        ) {
            transaction.setType(TransactionType.TAKE);
            return new TransactionDto(repository.save(transaction));
        }

        if (readerId.equals(lastTransaction.getReader().getId())) {
            transaction.setType(TransactionType.RETURN);
            return new TransactionDto(repository.save(transaction));
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Impossible to take book already taken by someone"
        );
    }

    public List<TransactionDto> getAllTransactions() {
        var transactions = repository.findAll();
        return transactions.stream().map(TransactionDto::new).toList();
    }

    public TransactionDto getTransaction(Long id) {
        var transaction = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found")
        );

        return new TransactionDto(transaction);
    }
}
