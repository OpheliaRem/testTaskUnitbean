package org.example.testtask.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Author;
import org.springframework.stereotype.Service;
import org.example.testtask.repositories.AuthorRepository;
import org.example.testtask.services.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

    @Override
    public void createAuthor(Author author) {
        repository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateAuthor(Author author) {
        repository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }
}
