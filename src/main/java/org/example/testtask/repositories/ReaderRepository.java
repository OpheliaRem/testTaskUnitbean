package org.example.testtask.repositories;

import org.example.testtask.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query(
            value = "select " +
                    "r.id, " +
                    "r.first_name, " +
                    "r.last_name, " +
                    "r.gender, " +
                    "r.date_of_birth, " +
                    "r.phone_number" +
                    " from readers r " +
                    "where r.id = (select reader_id " +
                    "from transactions t where t.type = 'take' group by t.reader_id" +
                    " order by count(*) desc limit 1) ",
            nativeQuery = true
    )
    Reader getReaderWithMajorityOfTakenBooks();

}
