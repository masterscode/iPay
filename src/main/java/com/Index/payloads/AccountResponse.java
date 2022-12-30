package com.Index.payloads;


import com.Index.providers.flutterwave.dto.FLWAccountDetail;
import lombok.Data;

@Data
public class AccountResponse {

   private String  accountNumber;
    private String accountName;
    private String bankCode;
    private String bankName;

    public AccountResponse(FLWAccountDetail accountDetail){
        accountName = accountDetail.getAccount_name();
        accountNumber = accountDetail.getAccount_number();
        bankCode = accountDetail.getAccount_bank();
    }
}
