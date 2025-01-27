package org.example.testtask.services;

import org.example.testtask.model.Author;

import java.util.List;

public interface AuthorService {

    void createAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthor(Long id);

    void updateAuthor(Author author);

    void deleteAuthor(Long id);

}
