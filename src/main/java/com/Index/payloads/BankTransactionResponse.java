package com.Index.payloads;

import lombok.Data;

@Data
public class BankTransactionResponse {

    private String amount;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankCode;
    private String transactionReference;
    private String transactionDateTime;
    private String currencyCode;
    private String responseMessage;
    private String responseCode;
    private String sessionId;
    private String status;

}
