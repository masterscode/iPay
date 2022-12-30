package com.Index.providers.paystack.dto;


import lombok.Data;

@Data
public class PayStackBank {
    private int id;
    private String name;
    private String slug;
    private String code;
    private String longcode;
    private String gateway;
    private boolean pay_with_bank;
    private boolean active;
    private String country;
    private String currency;
    private String type;
    private boolean is_deleted;
    private String createdAt;
    private String updatedAt;
}
