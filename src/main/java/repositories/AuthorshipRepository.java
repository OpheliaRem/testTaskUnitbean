package repositories;

import model.Authorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorshipRepository extends JpaRepository<Authorship, Long> {}
