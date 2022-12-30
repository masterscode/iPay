package com.Index.configs;


import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
