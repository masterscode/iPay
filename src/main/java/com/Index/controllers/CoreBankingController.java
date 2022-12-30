package com.Index.controllers;


import com.Index.exception.BadRequestException;
import com.Index.payloads.*;
import com.Index.providers.CoreBankingProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/core-banking")
public class CoreBankingController {
    private final Map<String, CoreBankingProvider> coreBankingProvider;

    @GetMapping("/banks")
    public ResponseEntity<Collection<NIPBank>> getBanks(@RequestParam(required = false, defaultValue = "FLUTTERWAVE") String provider) {
        validateProvider(provider);

        return ResponseEntity.ok(coreBankingProvider.get(provider).nipBanks());

    }

    @PostMapping("/validateBankAccount")
    public ResponseEntity<AccountResponse> validateBankAccount(@Valid @RequestBody ValidateAccountRequestDto dto,
                                                               @RequestParam(required = false, defaultValue = "FlutterWave") String provider
    ) {
        validateProvider(provider);
        return ResponseEntity.ok(
                coreBankingProvider.get(provider).nameEnquiry(dto)
        );
    }

    @PostMapping("/bankTransfer")
    public ResponseEntity<BankTransferResponse> bankTransfer(@Valid @RequestBody BankTransferRequest request, @RequestParam(required = false, defaultValue = "FlutterWave") String provider) {
        validateProvider(provider);
        return null;
    }

    @GetMapping("/transaction/{transactionReference}")
    public ResponseEntity<?> transactionStatus(@PathVariable String transactionReference) {
        return null;
    }


    private void validateProvider(final String provider) {
        if (StringUtils.isNotBlank(provider) && BooleanUtils.isFalse(coreBankingProvider.containsKey(provider)))
            throw new BadRequestException("Unknown provider");
    }
}
