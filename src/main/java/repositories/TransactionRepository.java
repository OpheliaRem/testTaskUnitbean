package repositories;

import model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(
            value="select count(*)" +
                    "from transactions as t" +
                    "where t.reader_id = :readerId and t.book_id = :bookId" +
                    "and t.type = 'take'",
            nativeQuery=true
    )
    Integer countAllTakeTransactions(Long readerId, Long bookId);

    @Query(
            value="select count(*)" +
                    "from transactions as t" +
                    "where t.reader_id = :readerId and t.book_id = :bookId" +
                    "and t.type = 'give_back'",
            nativeQuery=true
    )
    Integer countAllGiveBackTransactions(Long readerId, Long bookId);

}
