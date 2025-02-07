package org.example.testtask.repositories;

import org.example.testtask.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(
            value = "select a.* " +
                    "from authors a " +
                    "inner join " +
                    "   (select au.author_id from transactions t " +
                    "   inner join books b on b.id=t.book_id " +
                    "       inner join authorship_units au " +
                    "       on au.book_id=b.id " +
                    "   where t.type=0 " +
                "       and t.time_of_operation >= :start" +
                    " and t.time_of_operation <= :end " +
                "       group by au.author_id order by count(*) desc limit 1) " +
                    "ids on a.id=ids.author_id",
            nativeQuery = true
    )
    Author getAuthorByQuantityOfTakeTransactions(LocalDate start, LocalDate end);

}
