package com.Index.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String provider;
    private String amount;
    private String currencyCode;
    private String narration;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankCode;
    private String transactionReference;
    private String callBackUrl;
    private int maxRetryAttempt;
    private int retryAttempt;

    private String status = "PENDING";
    private String sessionId;
}
