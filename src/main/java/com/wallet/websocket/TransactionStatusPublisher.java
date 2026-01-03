package com.wallet.websocket;

import com.wallet.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionStatusPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void publish(Transaction tx) {
        messagingTemplate.convertAndSend(
                "/topic/transaction-status",
                tx
        );
    }
}
