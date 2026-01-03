package com.wallet.controller;

import com.wallet.entity.*;
import com.wallet.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class WalletController {

    private final WalletRepository walletRepo;
    private final TransactionRepository txRepo;

    public WalletController(WalletRepository walletRepo, TransactionRepository txRepo) {
        this.walletRepo = walletRepo;
        this.txRepo = txRepo;
    }

    // Wallet Balance
    @GetMapping("/wallet/balance")
    public Double getBalance(@RequestParam Long userId) {
        return walletRepo.findById(userId)
                .map(Wallet::getBalance)
                .orElse(0.0);
    }

    // Make Transaction
    @PostMapping("/transaction")
    public String makeTransaction(@RequestBody Transaction tx) {

        Wallet wallet = walletRepo.findById(tx.getUserId())
                .orElseGet(() -> {
                    Wallet w = new Wallet();
                    w.setUserId(tx.getUserId());
                    w.setBalance(0.0);
                    return w;
                });

        if ("CREDIT".equals(tx.getType())) {
            wallet.setBalance(wallet.getBalance() + tx.getAmount());
        } else {
            wallet.setBalance(wallet.getBalance() - tx.getAmount());
        }

        walletRepo.save(wallet);

        tx.setStatus("SUCCESS");
        tx.setTimestamp(LocalDateTime.now());
        txRepo.save(tx);

        return "Transaction successful";
    }

    // Transaction History
    @GetMapping("/transactions/history")
    public List<Transaction> history(@RequestParam Long userId) {
        return txRepo.findByUserId(userId);
    }
}
