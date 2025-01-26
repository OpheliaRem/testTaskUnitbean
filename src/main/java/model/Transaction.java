package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Transaction {

    private String type;

    private LocalDateTime timeOfOperation;

    private Reader reader;

    private Book book;
}
