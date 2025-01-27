package org.example.testtask.services;

import org.example.testtask.model.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(Transaction transaction) throws Exception;

    List<Transaction> getAllTransactions();

    Transaction getTransaction(Long id);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);
}
