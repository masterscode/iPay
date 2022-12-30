package com.Index.providers.flutterwave.dto;

import com.Index.payloads.BankTransferRequest;
import lombok.Data;

@Data
public class FLWTransferRequest {
   private String account_bank;
    private String account_number;
    private double amount;
    private String narration;
    private String currency;
    private String reference;
    private String callback_url;
    private String debit_currency;


    public FLWTransferRequest(BankTransferRequest request){
        account_bank = request.getBeneficiaryBankCode();
        account_number = request.getBeneficiaryAccountNumber();
        amount = Double.parseDouble(request.getAmount());
        narration = request.getNarration();
        currency = request.getCurrencyCode();
        reference = request.getTransactionReference();
        callback_url = request.getCallBackUrl();
        debit_currency = request.getCurrencyCode();
    }
}
