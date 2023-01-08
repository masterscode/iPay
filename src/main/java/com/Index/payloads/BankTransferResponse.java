package com.Index.payloads;

import com.Index.entities.Transaction;
import com.Index.providers.flutterwave.dto.FLWTransferResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankTransferResponse {

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

    public BankTransferResponse(Transaction t) {
        amount = t.getAmount();
        beneficiaryAccountNumber = t.getBeneficiaryAccountNumber();
        beneficiaryAccountName = t.getBeneficiaryAccountName();
        beneficiaryBankCode = t.getBeneficiaryBankCode();
        transactionReference = t.getTransactionReference();
        transactionDateTime = "n/a";
        currencyCode = t.getCurrencyCode();
        responseMessage = "n/a";
        responseCode = "n/a";
        sessionId = t.getSessionId();
        status = t.getStatus();
    }

    public BankTransferResponse(FLWTransferResponse response){
        amount = response.getAmount().toString();
        beneficiaryAccountNumber = response.getAccount_number();
        beneficiaryAccountName = response.getFull_name();
        beneficiaryBankCode = response.getBank_code();
        transactionReference = response.getReference();
        transactionDateTime = response.getCreated_at();
        currencyCode = response.getCurrency();
        responseMessage = response.getComplete_message();
    }

    public BankTransferResponse(ApiResponse response){
        status = response.getStatus();
        responseMessage = response.getMessage();

    }

}
