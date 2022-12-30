package com.Index.providers.flutterwave.service;

import com.Index.configs.ConfigProperties;
import com.Index.exception.BadRequestException;
import com.Index.payloads.*;
import com.Index.providers.CoreBankingProvider;
import com.Index.providers.ProviderManager;
import com.Index.providers.flutterwave.FlutterWaveURIs;
import com.Index.providers.flutterwave.dto.FLWAccountDetail;
import com.Index.providers.flutterwave.dto.FLWNIPBank;
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
@Service("FLUTTERWAVE")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FlutterWaveService implements CoreBankingProvider {
    private final HttpClient httpClient;
    private final ConfigProperties configProperties;
    private final ProviderManager providerManager;

    @Override
    public Collection<NIPBank> nipBanks() {
        Collection<NIPBank> nipBanks = providerManager.getProviderBanks("FLUTTERWAVE");

        if (BooleanUtils.isFalse(nipBanks.isEmpty())) return nipBanks;

        final String url = configProperties.getFlutterWave().getBaseUrl().concat(FlutterWaveURIs.LIST_BANKS);

        try (Response response = httpClient.get(getHeader(), new HashMap<>(), url)) {
            final String responseJson = response.body().string();
            ApiResponse apiResponse = httpClient.toPojo(responseJson, ApiResponse.class);
            List<FLWNIPBank> bankList = httpClient.toPojo(httpClient.toJson(apiResponse.getData()), new TypeToken<List<FLWNIPBank>>() {
            }.getType());
            return
                    bankList.stream().map(NIPBank::new).toList();
        } catch (Exception e) {
            log.error("--> NIPBanks exception :: ", e);
        }
        return Collections.emptyList();
    }

    @Override
    public AccountResponse nameEnquiry(ValidateAccountRequestDto validateAccountRequestDto) {
        final String url = configProperties.getFlutterWave().getBaseUrl().concat(FlutterWaveURIs.NAME_ENQUIRY);
        final String payload = httpClient.toJson(new FLWAccountDetail(validateAccountRequestDto));
        try (Response response = httpClient.post(getHeader(), payload, url)) {
            final String responseJson = response.body().string();

            ApiResponse apiResponse = httpClient.toPojo(responseJson, ApiResponse.class);

            if (Objects.isNull(apiResponse.getData()))
                throw new BadRequestException(apiResponse.getMessage());

            FLWAccountDetail accountDetail = httpClient.toPojo(httpClient.toJson(apiResponse.getData()), FLWAccountDetail.class);

            AccountResponse accountResponse = new AccountResponse(accountDetail);
            accountResponse.setAccountName(providerManager.getBankName(validateAccountRequestDto.getCode()));

            return accountResponse;

        } catch (Exception e) {
            log.error("--> NIPBanks exception :: ", e);
            throw new BadRequestException(e.getMessage());
        }
    }


    public BankTransferResponse bankTransaction(BankTransferRequest request){
        //checks --> true
        //save Transaction
        return null;
    }

    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", configProperties.getFlutterWave().getSecret());
        return header;
    }
}
