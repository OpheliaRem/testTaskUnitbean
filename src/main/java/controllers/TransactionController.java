package controllers;

import lombok.AllArgsConstructor;
import model.Transaction;
import org.springframework.web.bind.annotation.*;
import services.TransactionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService service;

    @PostMapping("/create")
    public void createTransaction(
            @RequestParam Long readerId,
            @RequestParam Long bookId
    ) {
        try {
            service.createTransaction(readerId, bookId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/find/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return service.getTransaction(id);
    }

    @PutMapping("/update")
    public void updateTransaction(@RequestBody Transaction transaction) {
        service.updateTransaction(transaction);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        service.deleteTransaction(id);
    }
}
