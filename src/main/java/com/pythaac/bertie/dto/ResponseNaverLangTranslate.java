package com.pythaac.bertie.dto;

import java.util.Map;

public class ResponseNaverLangTranslate {
    private Map<String, Object> message;

    public String getResult(){
        return ((Map<String, String>)message.get("result")).get("translatedText");
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}
