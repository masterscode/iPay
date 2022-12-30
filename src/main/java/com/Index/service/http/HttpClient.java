package com.Index.service.http;

import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public interface HttpClient {
    Response post(Map<String, String> headerList, String jsonPayload, String url) throws IOException;

    Response get(Map<String, String> headerList, Map<String, Object> params, String url) throws IOException;

    <T> T toPojo(String json, Class<T> type);

    <T> T toPojo(String json, Type type);

    String toJson(Object src);
}
