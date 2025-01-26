package services;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(
            Long readerId,
            Long bookId
    ) throws Exception;

    List<Transaction> getAllTransactions();

    Transaction getTransaction(Long id);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);
}
