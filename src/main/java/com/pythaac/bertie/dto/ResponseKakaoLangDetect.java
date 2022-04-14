package com.pythaac.bertie.dto;

import java.util.ArrayList;

public class ResponseKakaoLangDetect {
    private ArrayList<LangInfo> language_info;

    public ResponseKakaoLangDetect() {
        language_info = new ArrayList<>();
    }

    public String getLangCode(){
        language_info.sort((x, y) -> x.getConfidence().compareTo(y.getConfidence()));
        return language_info.get(language_info.size()-1).getCode();
    }
}

class LangInfo{
    private String code;
    private String name;
    private Double confidence;

    public LangInfo(String code, String name, Double confidence) {
        this.code = code;
        this.name = name;
        this.confidence = confidence;
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