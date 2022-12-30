package com.Index.providers.paystack.service;

import com.Index.configs.ConfigProperties;
import com.Index.exception.BadRequestException;
import com.Index.payloads.AccountResponse;
import com.Index.payloads.ApiResponse;
import com.Index.payloads.NIPBank;
import com.Index.payloads.ValidateAccountRequestDto;
import com.Index.providers.CoreBankingProvider;
import com.Index.providers.ProviderManager;
import com.Index.providers.paystack.PayStackURIs;
import com.Index.providers.paystack.dto.PayStackAccountResolve;
import com.Index.providers.paystack.dto.PayStackBank;
import com.Index.service.http.HttpClient;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service("PAYSTACK")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PayStackService implements CoreBankingProvider {
    private final HttpClient httpClient;
    private final ConfigProperties configProperties;
    private final ProviderManager providerManager;

    @Override
    public Collection<NIPBank> nipBanks() {
        log.info("--> using PayStack as provider <--");
        Collection<NIPBank> nipBanks = providerManager.getProviderBanks("PAYSTACK");

        if (BooleanUtils.isFalse(nipBanks.isEmpty())) return nipBanks;

        final String url = configProperties.getPayStack().getBaseUrl().concat(PayStackURIs.BANK);
        try (Response response = httpClient.get(getHeader(), new HashMap<>(), url)) {
            final String responseJson = response.body().string();
            ApiResponse apiResponse = httpClient.toPojo(responseJson, ApiResponse.class);

            List<PayStackBank> bankList = httpClient.toPojo(httpClient.toJson(apiResponse.getData()), new TypeToken<List<PayStackBank>>() {
            }.getType());

            return
                    bankList.stream().map(NIPBank::new).toList();
        } catch (Exception e) {
            log.error("pay-stack error {}", e);
            return Collections.emptyList();
        }

    }

    @Override
    public AccountResponse nameEnquiry(ValidateAccountRequestDto dto) {
        log.info("--> using PayStack as provider <--");

        final String uri = String.format(PayStackURIs.NAME_ENQUIRY, dto.getAccountNumber(), dto.getCode());
        final String url = configProperties.getPayStack().getBaseUrl().concat(uri);

        try (Response response = httpClient.get(getHeader(), new HashMap<>(), url)) {
            final String responseJson = response.body().string();
            ApiResponse apiResponse = httpClient.toPojo(responseJson, ApiResponse.class);
            final String data = httpClient.toJson(apiResponse.getData());

            AccountResponse accountResponse =new AccountResponse(
                    httpClient.toPojo(data, PayStackAccountResolve.class)
            );
            accountResponse.setBankCode(dto.getCode());
            accountResponse.setBankName(providerManager.getBankName(dto.getCode()));
            return accountResponse;


        } catch (Exception e) {
            log.error("pay-stack error {}", e);
            throw new BadRequestException(e.getMessage());
        }
    }

    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "Bearer " + configProperties.getPayStack().getSecret());
        return header;
    }
}
