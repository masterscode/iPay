package com.Index.providers;

import com.Index.entities.Transaction;
import com.Index.payloads.*;

import java.util.Collection;

public interface CoreBankingProvider {
    Collection<NIPBank> nipBanks();

    AccountResponse nameEnquiry(ValidateAccountRequestDto validateAccountRequestDto);

    BankTransferResponse processTransaction(Transaction request);
}
