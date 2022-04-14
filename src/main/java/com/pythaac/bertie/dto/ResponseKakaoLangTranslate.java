package com.pythaac.bertie.dto;

import com.pythaac.bertie.exception.ApiFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResponseKakaoLangTranslate {
    private ArrayList<ArrayList<String>> translated_text;

    public ResponseKakaoLangTranslate() {
        translated_text = new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> getTranslated_text() {
        return translated_text;
    }

    public void setTranslated_text(ArrayList<ArrayList<String>> translated_text) {
        this.translated_text = translated_text;
    }

    public String getTranslatedText() {
        List<String> sumPhrase = new ArrayList<>();
        translated_text.forEach(phrase ->
                        phrase.stream()
                            .reduce((x, y) -> x + " " + y)
                            .ifPresent(sumPhrase::add));
        Optional<String> sumAll = sumPhrase.stream().reduce((x, y) -> x + "\n" + y);

        if (sumAll.isEmpty())
            return "";
        else
            return sumAll.get();
    }
}
