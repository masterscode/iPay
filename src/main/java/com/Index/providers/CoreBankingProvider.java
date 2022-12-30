package com.Index.providers;

import com.Index.payloads.NIPBank;
import com.Index.payloads.ValidateAccountRequestDto;

import java.util.Collection;

public interface CoreBankingProvider {
    Collection<NIPBank> nipBanks();

    Object nameEnquiry(ValidateAccountRequestDto validateAccountRequestDto);
}
