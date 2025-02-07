package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Transaction;
import org.example.testtask.model.transactionType.TransactionType;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    private Long id;

    private TransactionType type;

    private LocalDateTime timeOfOperation;

    private ReaderDTO readerDTO;

    private BookDTO bookDTO;

    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        timeOfOperation = transaction.getTimeOfOperation();
        readerDTO = new ReaderDTO(transaction.getReader());
        bookDTO = new BookDTO(transaction.getBook());
    }

}
