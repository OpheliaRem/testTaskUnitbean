package services.impl;

import lombok.AllArgsConstructor;
import model.Authorship;
import org.springframework.stereotype.Service;
import repositories.AuthorshipRepository;
import services.AuthorshipService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorshipServiceImpl implements AuthorshipService {

    AuthorshipRepository repository;

    @Override
    public void createAuthorship(Authorship authorship) {
        repository.save(authorship);
    }

    @Override
    public List<Authorship> getAllAuthorshipUnits() {
        return repository.findAll();
    }

    @Override
    public Authorship getAuthorship(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateAuthorship(Authorship authorship) {
        repository.save(authorship);
    }

    @Override
    public void deleteAuthorship(Long id) {
        repository.deleteById(id);
    }
}
