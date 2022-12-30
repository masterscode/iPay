package com.Index.service.http;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class HttpImplementation implements HttpClient {
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(HttpImplementation.class);

    @Override
    public Response post(Map<String, String> headerList, String jsonPayload, String url) throws IOException {
        logger.info("Making POST request with header {}, jsonPayload {} and url {}", headerList, jsonPayload, url);

        Request request = new Request.Builder().post(
                RequestBody.create(jsonPayload, MediaType.parse("application/json"))
        ).headers( Headers.of(headerList)).url(url).build();



        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    @Override
    public Response get(Map<String, String> headerList, Map<String, Object> params, String url) throws IOException {
        logger.info("Making GET request with header {}, params {} and url {}", headerList, params, url);
        URL httpUrl = UriComponentsBuilder.fromHttpUrl(url).build(params).toURL();

        Request request = new Request.Builder().get().headers(Headers.of(headerList)).url(httpUrl).build();


        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    @Override
    public <T> T toPojo(final String json, Class<T> type){
        logger.info("--> converting {} to object type of {}", json, type);

        return gson.fromJson(json, type);
    }

    @Override
    public <T> T toPojo(final String json, Type type){
        logger.info("--> converting {} to object type of {}", json, type);
        return gson.fromJson(json, type);
    }

    @Override
    public String toJson(final Object src){
        logger.info("--> converting {} to json string", src);
        return gson.toJson(src);
    }

}
