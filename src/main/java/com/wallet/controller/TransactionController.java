package com.wallet.controller;

import com.wallet.dto.TransactionRequest;
import com.wallet.entity.Transaction;
import com.wallet.kafka.TransactionProducer;
import com.wallet.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionProducer kafkaProducer;

    public TransactionController(TransactionRepository transactionRepository,
                                 TransactionProducer kafkaProducer) {
        this.transactionRepository = transactionRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request) {

        Transaction tx = new Transaction();
        tx.setTxId(UUID.randomUUID().toString());
        tx.setAmount(request.getAmount());
        tx.setCreatedAt(LocalDateTime.now());
        tx.setStatus(String.valueOf(Transaction.Status.PENDING));

        transactionRepository.save(tx);

        kafkaProducer.sendTransactionEvent(tx.getTxId());

        return tx;
    }
}
