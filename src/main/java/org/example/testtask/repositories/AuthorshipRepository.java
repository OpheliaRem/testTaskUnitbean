package org.example.testtask.repositories;

import org.example.testtask.model.Authorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorshipRepository extends JpaRepository<Authorship, Long> {}
