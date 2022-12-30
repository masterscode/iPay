package com.Index.providers.flutterwave.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class FLWTransferResponse {
    BigDecimal amount;
    String account_number;
    String full_name;
    String bank_code;
    String reference;
    String created_at;
    String currency;
    String complete_message;
    String status;
}
