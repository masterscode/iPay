package com.Index.payloads;

import lombok.Data;


@Data
public class ApiResponse {
    private String status;
    private String message;

    private Object data;
}
