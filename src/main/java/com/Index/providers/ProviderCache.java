package com.Index.providers;

import com.Index.payloads.NIPBank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class ProviderCache {
    @Bean
    public Cache<String, NIPBank> bankResponseCache() {

        MutableConfiguration<String, NIPBank> configuration = new MutableConfiguration<String, NIPBank>()
                .setTypes(String.class, NIPBank.class)
                .setStoreByValue(false)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.DAYS, 14)));


        return Caching.getCachingProvider()
                .getCacheManager().createCache("NIP_BANKS_CACHE", configuration);
    }
}
