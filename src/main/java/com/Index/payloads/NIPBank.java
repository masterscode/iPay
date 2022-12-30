package com.Index.payloads;

import com.Index.providers.flutterwave.dto.FLWNIPBank;
import com.Index.providers.paystack.dto.PayStackBank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NIPBank {
    private String code;
    private String bankName;
    private String longCode;

    public NIPBank(FLWNIPBank bank){
        code = bank.getCode();
        bankName = bank.getName();
    }
    public NIPBank(PayStackBank bank){
        code = bank.getCode();
        bankName = bank.getName();
        longCode = bank.getLongcode();
    }
}
