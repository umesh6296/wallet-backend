package com.wallet.controller;

import com.wallet.dto.TransactionEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @PostMapping
    public String makeTransaction(@RequestBody TransactionEvent event) {
        // TODO: send event to Kafka
        return "Transaction initiated";
    }
}
