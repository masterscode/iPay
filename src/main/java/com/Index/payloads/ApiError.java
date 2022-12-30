package com.Index.payloads;


import lombok.Data;

@Data
public class ApiError {
    private String code = "96";
    private String description;
    public ApiError(String description){
        this.description = description;
    }

}
