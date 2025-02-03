package org.example.testtask.repositories;

import org.example.testtask.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query(
            value = "select r.* " +
                    "from readers r " +
                    "where r.id = " +
                    "   (select reader_id " +
                    "   from transactions t where t.type = 0 group by t.reader_id" +
                    "   order by count(*) desc limit 1) ",
            nativeQuery = true
    )
    Reader findReaderWithMajorityOfTakenBooks();

    @Query(
            value = "select r.* " +
                    "from readers r " +
                    "inner join " +
                    "(select first.reader_id, " +
                    "coalesce(first.count, 0)-coalesce(second.count, 0) diff " +
                    "from (select reader_id, count(*) " +
                    "from transactions where type=0 group by reader_id) first " +
                    "full outer join " +
                    "(select reader_id, count(*) from transactions where type=1 " +
                    "group by reader_id) second " +
                    "on first.reader_id = second.reader_id) " +
                    "ids on ids.reader_id=r.id order by diff desc",
            nativeQuery = true
    )
    List<Reader> findAllReadersSortedByUnreturnedBooks();

}
