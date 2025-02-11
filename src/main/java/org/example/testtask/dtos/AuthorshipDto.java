package org.example.testtask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.model.Authorship;

@Data
@NoArgsConstructor
public class AuthorshipDto {

    private Long id;

    private Long authorId;

    private Long bookId;

    public AuthorshipDto(Authorship authorship) {
        id = authorship.getId();
        authorId = authorship.getAuthor().getId();
        bookId = authorship.getBook().getId();
    }

}
