package com.pythaac.bertie.dto;

import com.pythaac.bertie.exception.ApiFailedException;

import java.util.ArrayList;
import java.util.Optional;

public class ResponseKakaoLangTranslate {
    private ArrayList<String> translated_text;

    public ResponseKakaoLangTranslate() {
        translated_text = new ArrayList<>();
    }

    public String getTranslatedText() {
        Optional<String> translated = translated_text.stream().reduce((x, y) -> x + " " + y);
        if (translated.isEmpty())
            return "";
        else
            return translated.get();
    }
}
