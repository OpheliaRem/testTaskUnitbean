package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping("/")
    public void makeTransaction() throws Exception {
        throw new Exception("Not implemented yet");
    }

}
