package services.impl;

import lombok.AllArgsConstructor;
import model.Book;
import org.springframework.stereotype.Service;
import repositories.BookRepository;
import services.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    BookRepository repository;

    @Override
    public void createBook(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateBook(Book book) {
        repository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
