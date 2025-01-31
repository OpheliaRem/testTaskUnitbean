package org.example.testtask.repositories;

import org.example.testtask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(
            value = "select count(*) from books b " +
                    "inner join transactions t on b.id = t.book_id " +
                    "where t.reader_id = :readerId and t.type='take'",
            nativeQuery = true
    )
    Long countTakenBooksByReaderId(Long readerId);

    @Query(
            value = "select count(*) from books b " +
                    "inner join transactions t on b.id = t.book_id " +
                    "where t.reader_id = :readerId and t.type='give back'",
            nativeQuery = true
    )
    Long countReturnedBooksByReaderId(Long readerId);
}
