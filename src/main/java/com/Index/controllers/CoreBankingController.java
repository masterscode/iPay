package com.Index.controllers;


import com.Index.exception.BadRequestException;
import com.Index.payloads.*;
import com.Index.providers.CoreBankingProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/core-banking")
public class CoreBankingController {
    private final Map<String, CoreBankingProvider> coreBankingProvider;

    @GetMapping("/banks")
    public ResponseEntity<Collection<NIPBank>> getBanks(@RequestParam(required = false, defaultValue = "FLUTTERWAVE") String provider) {
        provider(provider);

        return ResponseEntity.ok(coreBankingProvider.get(provider).nipBanks());

    }

    @PostMapping("/validateBankAccount")
    public ResponseEntity<AccountResponse> validateBankAccount(@Valid @RequestBody ValidateAccountRequestDto dto,
                                                               @RequestParam(required = false, defaultValue = "FlutterWave") String provider
    ) {
       ;
        return ResponseEntity.ok(
                 provider(provider).nameEnquiry(dto)
        );
    }

    @PostMapping("/bankTransfer")
    public ResponseEntity<Object>
    bankTransfer(@Valid @RequestBody BankTransferRequest request, @RequestParam(required = false, defaultValue = "FLUTTERWAVE") String provider) {
        return ResponseEntity.ok(provider(provider).initTransaction(request));
    }

    @GetMapping("/transaction/{transactionReference}")
    public ResponseEntity<?> transactionStatus(@PathVariable String transactionReference) {
        return null;
    }


    private CoreBankingProvider provider(final String provider) {
        return Optional.ofNullable(coreBankingProvider.get(provider))
                .orElseThrow(()-> new BadRequestException("Unknown provider"));

    }
}
