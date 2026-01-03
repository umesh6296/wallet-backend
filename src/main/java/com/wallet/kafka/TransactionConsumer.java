package com.wallet.kafka;

import com.wallet.entity.Transaction;
import com.wallet.repository.TransactionRepository;
import com.wallet.websocket.TransactionStatusPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionConsumer {

    private final TransactionRepository repository;
    private final TransactionStatusPublisher publisher;

    @KafkaListener(topics = "transaction-created", groupId = "tx-group")
    public void processTransaction(String txId) throws InterruptedException {

        // simulate processing delay
        Thread.sleep(5000);

        Transaction tx = repository.findByTxId(txId)
                .orElseThrow();

        // mock validation
        if (Math.random() > 0.3) {
            tx.setStatus(String.valueOf(Transaction.Status.SUCCESS));
        } else {
            tx.setStatus(String.valueOf(Transaction.Status.FAILURE));
        }

        repository.save(tx);

        // notify frontend
        publisher.publish(tx);
    }
}
