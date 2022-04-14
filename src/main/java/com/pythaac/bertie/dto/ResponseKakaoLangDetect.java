package com.pythaac.bertie.dto;

import java.util.ArrayList;

public class ResponseKakaoLangDetect {
    private String code;
    private String name;
    private Double confidence;

    public ResponseKakaoLangDetect(String code, String name, Double confidence) {
        this.code = code;
        this.name = name;
        this.confidence = confidence;
    }

    public ResponseKakaoLangDetect() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}