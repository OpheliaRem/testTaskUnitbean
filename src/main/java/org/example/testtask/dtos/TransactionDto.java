package org.example.testtask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.model.Transaction;
import org.example.testtask.model.transactionType.TransactionType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionDto {

    private Long id;

    private TransactionType type;

    private LocalDateTime timeOfOperation;

    private Long readerId;

    private Long bookId;


    public TransactionDto(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        timeOfOperation = transaction.getTimeOfOperation();
        readerId = transaction.getReader().getId();
        bookId = transaction.getBook().getId();
    }

}
