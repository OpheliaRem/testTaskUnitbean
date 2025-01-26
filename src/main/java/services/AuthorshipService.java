package services;

import model.Authorship;

import java.util.List;

public interface AuthorshipService {

    void createAuthorship(Authorship authorship);

    List<Authorship> getAllAuthorshipUnits();

    Authorship getAuthorship(Long id);

    void updateAuthorship(Authorship authorship);

    void deleteAuthorship(Long id);

}
