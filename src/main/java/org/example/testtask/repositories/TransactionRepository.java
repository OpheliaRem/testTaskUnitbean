package org.example.testtask.repositories;

import org.example.testtask.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(
            value="select * from transactions t" +
                    " where t.book_id = :bookId" +
                    " order by time_of_operation desc limit 1",
            nativeQuery=true
    )
    Transaction getLastTransactionWhereBookId(Long bookId);
}
