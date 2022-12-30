package com.Index.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateAccountRequestDto {
   @NotNull(message = "Bank code is required")
    private String  code;
    @NotNull(message = "Account number is required")
   private String accountNumber;
}
