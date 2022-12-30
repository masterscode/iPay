package com.Index.providers.paystack.service;

import com.Index.configs.ConfigProperties;
import com.Index.exception.BadRequestException;
import com.Index.payloads.ApiResponse;
import com.Index.payloads.NIPBank;
import com.Index.payloads.ValidateAccountRequestDto;
import com.Index.providers.CoreBankingProvider;
import com.Index.providers.flutterwave.dto.FLWNIPBank;
import com.Index.providers.paystack.PayStackURIs;
import com.Index.providers.paystack.dto.PayStackBank;
import com.Index.service.http.HttpClient;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service("PAYSTACK")
@RequiredArgsConstructor
public class PayStackService implements CoreBankingProvider {
    private final HttpClient httpClient;
    private final ConfigProperties configProperties;

    @Override
    public Collection<NIPBank> nipBanks() {
        log.info("--> using PayStack as provider <--");
        final String url = configProperties.getPayStack().getBaseUrl().concat(PayStackURIs.NAME_ENQUIRY);
        try (Response response = httpClient.get(getHeader(), new HashMap<>(), url)) {
            final String responseJson = response.body().string();
            ApiResponse apiResponse = httpClient.toPojo(responseJson, ApiResponse.class);

            List<PayStackBank> bankList = httpClient.toPojo(httpClient.toJson(apiResponse.getData()), new TypeToken<List<PayStackBank>>() {
            }.getType());

            return
                    bankList.stream().map(NIPBank::new).toList();
        } catch (Exception e) {
            log.error("paystack error {}", e);
            throw new BadRequestException(e.getMessage());
        }

    }

    @Override
    public Object nameEnquiry(ValidateAccountRequestDto validateAccountRequestDto) {
        return null;
    }

    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", configProperties.getPayStack().getSecret());
        return header;
    }
}
