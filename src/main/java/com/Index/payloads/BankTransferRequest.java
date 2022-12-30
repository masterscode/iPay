package com.Index.payloads;


import com.Index.entities.Transaction;
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

    public Transaction toEntity() {
        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setCurrencyCode(currencyCode);
        t.setNarration(narration);
        t.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
        t.setBeneficiaryAccountName(beneficiaryAccountName);
        t.setBeneficiaryBankCode(beneficiaryBankCode);
        t.setTransactionReference(transactionReference);
        t.setCallBackUrl(callBackUrl);
        t.setMaxRetryAttempt(maxRetryAttempt);
        return t;
    }
}
