package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {

    private enum Type {
        TAKE,
        GIVE_BACK
    }

    private Type type;

    private LocalDateTime timeOfOperation;

    private Reader reader;

    private Book book;
}
