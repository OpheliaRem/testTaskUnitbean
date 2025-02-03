package org.example.testtask.dtos;

import lombok.Data;
import org.example.testtask.model.Authorship;

@Data
public class AuthorshipDTO {

    private Long id;

    private AuthorDTO authorDTO;

    private BookDTO bookDTO;

    public AuthorshipDTO(Authorship authorship) {
        id = authorship.getId();
        authorDTO = new AuthorDTO(authorship.getAuthor());
        bookDTO = new BookDTO(authorship.getBook());
    }

}
