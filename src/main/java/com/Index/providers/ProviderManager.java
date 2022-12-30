package com.Index.providers;


import com.Index.payloads.NIPBank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class ProviderManager {

    private final Cache<String, NIPBank> bankResponseCache;
    private final Map<String, CoreBankingProvider> coreBankingProviders;

    private final Map<String, Collection<NIPBank>> providerBanks = new ConcurrentHashMap<>();

    public String getBankName(final String code) {
        return Optional.ofNullable(bankResponseCache.get(code))
                .orElse(new NIPBank(code, "", ""))
                .getBankName();
    }

    public Collection<NIPBank> getProviderBanks(final String provider){
        return Optional.ofNullable(providerBanks.get(provider)).orElse(Collections.EMPTY_LIST);
    }
    @Scheduled(fixedRate = 604800000)// seven-days
    void updateSupportedInstitutionCache() {
        log.info("--> Provider banks list cache update started <--");

        coreBankingProviders
                .entrySet()
                .parallelStream()
                .flatMap(p -> {
                    Collection<NIPBank> banks = p.getValue().nipBanks();
                    providerBanks.put(p.getKey(), banks);
                    return banks.parallelStream();
                })
                .forEach(nipBank -> bankResponseCache.put(nipBank.getCode(), nipBank));

        System.out.println(
                providerBanks
        );
        log.info("--> Provider banks list cache update completed <--");

    }

}
