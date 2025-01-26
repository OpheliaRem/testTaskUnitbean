package services.impl;

import lombok.AllArgsConstructor;
import model.Transaction;
import org.springframework.stereotype.Service;
import repositories.BookRepository;
import repositories.ReaderRepository;
import repositories.TransactionRepository;
import services.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository repository;
    BookRepository bookRepository;
    ReaderRepository readerRepository;

    @Override
    public void createTransaction(
            Long readerId,
            Long bookId
    ) throws Exception {

        var reader = readerRepository.findById(readerId);
        var book = bookRepository.findById(bookId);

        if (reader.isEmpty()) {
            throw new Exception("No such reader found");
        }

        if (book.isEmpty()) {
            throw new Exception("No such book found");
        }

        var numberOfTaking = repository.countAllTakeTransactions(readerId, bookId);
        var numberOfGivingBack = repository.countAllGiveBackTransactions(readerId, bookId);
        var diffOfActions = numberOfTaking - numberOfGivingBack;

        //Если человек брал и возвращал одну и ту же книгу одинаковое число раз,
        //то в этот раз он снова её берёт, иначе он её возвращает
        String type = diffOfActions == 0 ? "take" : "give back";

        repository.save(new Transaction(
                type,
                LocalDateTime.now(),
                reader.get(),
                book.get()
        ));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public Transaction getTransaction(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
}
