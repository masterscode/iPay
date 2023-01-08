package com.Index.repos;

import com.Index.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionReference(String ref);
    boolean existsByTransactionReference(String ref);
}
