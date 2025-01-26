package controllers;

import lombok.AllArgsConstructor;
import model.Book;
import org.springframework.web.bind.annotation.*;
import services.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private BookService service;

    @PostMapping("/create")
    public void createBook(@RequestBody Book book) {
        service.createBook(book);
    }

    @GetMapping("/findAll")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/find/{id}")
    public Book getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @PutMapping("/update")
    public void updateBook(@RequestBody Book book) {
        service.updateBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

}
