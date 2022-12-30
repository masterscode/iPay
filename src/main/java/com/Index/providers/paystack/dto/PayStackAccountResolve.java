package com.Index.providers.paystack.dto;

import com.Index.payloads.ValidateAccountRequestDto;
import com.Index.providers.flutterwave.dto.FLWAccountDetail;
import lombok.Data;


public class PayStackAccountResolve extends FLWAccountDetail {
    public PayStackAccountResolve(ValidateAccountRequestDto dto) {
        super(dto);
    }
}
