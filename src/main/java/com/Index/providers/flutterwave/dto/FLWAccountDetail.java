package com.Index.providers.flutterwave.dto;


import com.Index.payloads.ValidateAccountRequestDto;
import lombok.Data;

@Data
public class FLWAccountDetail {
    private String account_number;
    private String account_bank;

    private String account_name;

    public FLWAccountDetail(ValidateAccountRequestDto dto) {
        account_bank = dto.getCode();
        account_number = dto.getAccountNumber();
    }
}
