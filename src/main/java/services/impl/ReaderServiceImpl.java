package services.impl;

import lombok.AllArgsConstructor;
import model.Reader;
import org.springframework.stereotype.Service;
import repositories.ReaderRepository;
import services.ReaderService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository repository;

    @Override
    public void createReader(Reader reader) {
        repository.save(reader);
    }

    @Override
    public List<Reader> getAllReaders() {
        return repository.findAll();
    }

    @Override
    public Reader getReader(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateReader(Reader reader) {
        repository.save(reader);
    }

    @Override
    public void deleteReader(Long id) {
        repository.deleteById(id);
    }
}
