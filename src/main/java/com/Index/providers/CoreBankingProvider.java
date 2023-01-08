package com.Index.providers;

import com.Index.payloads.*;

import java.util.Collection;

public interface CoreBankingProvider {
    Collection<NIPBank> nipBanks();

    AccountResponse nameEnquiry(ValidateAccountRequestDto validateAccountRequestDto);

    Object initTransaction(BankTransferRequest request);

    BankTransferResponse processTransaction(BankTransferRequest request);
}
