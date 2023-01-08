package com.Index.payloads;


import com.Index.entities.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BankTransferRequest {

    private String amount;
    private String currencyCode;
    private String narration;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankCode;
    private String transactionReference;
    private String callBackUrl;
    private int maxRetryAttempt;


    private String provider;

    public Transaction toEntity(String provider) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCurrencyCode(currencyCode);
        transaction.setNarration(narration);
        transaction.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
        transaction.setBeneficiaryAccountName(beneficiaryAccountName);
        transaction.setBeneficiaryBankCode(beneficiaryBankCode);
        transaction.setTransactionReference(transactionReference);
        transaction.setCallBackUrl(callBackUrl);
        transaction.setMaxRetryAttempt(maxRetryAttempt);
        transaction.setProvider(provider);
        return transaction;
    }
}
