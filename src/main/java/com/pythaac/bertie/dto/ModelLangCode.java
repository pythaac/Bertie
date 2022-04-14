package com.pythaac.bertie.dto;

public class ModelLangCode {
    private final String name;
    private final String code;

    public ModelLangCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
