package com.pythaac.bertie.dto;

import java.util.ArrayList;
import com.pythaac.bertie.dto.ResponseKakaoLangDetect;

public class ResponseKakaoLangDetectWrapper {
    private ArrayList<ResponseKakaoLangDetect> language_info;

    public ResponseKakaoLangDetectWrapper() {
        language_info = new ArrayList<>();
    }

    public ArrayList<ResponseKakaoLangDetect> getLanguage_info() {
        return language_info;
    }

    public void setLanguage_info(ArrayList<ResponseKakaoLangDetect> language_info) {
        this.language_info = language_info;
    }

    public String getLangCode(){
        language_info.sort((x, y) -> x.getConfidence().compareTo(y.getConfidence()));
        return language_info.get(language_info.size()-1).getCode();
    }
}
