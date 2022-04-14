package com.pythaac.bertie.service.LangaugeServices;

import com.pythaac.bertie.domain.NaverApiInfo;
import com.pythaac.bertie.dto.ModelLangCode;
import com.pythaac.bertie.dto.ResponseNaverLangDetect;
import com.pythaac.bertie.dto.ResponseNaverLangTranslate;
import com.pythaac.bertie.exception.ApiFailedException;
import com.pythaac.bertie.exception.NaverApiInfoNotExistException;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import com.pythaac.bertie.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class NaverLanguageService extends LanguageService {
    private final RestTemplate restTemplate;
    private final NaverApiInfoRepository apiInfoRepository;
    private final ArrayList<ModelLangCode> langCodes;

    public NaverLanguageService(NaverApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder.build();
        this.langCodes = new ArrayList<>();
        setLangCodes();
    }

    private void setLangCodes(){
        this.langCodes.add(new ModelLangCode("한국어", "ko"));
        this.langCodes.add(new ModelLangCode("영어", "en"));
        this.langCodes.add(new ModelLangCode("일본어", "ja"));
        this.langCodes.add(new ModelLangCode("중국어 간체", "zh-CN"));
        this.langCodes.add(new ModelLangCode("중국어 번체", "zh-TW"));
        this.langCodes.add(new ModelLangCode("베트남어", "vi"));
        this.langCodes.add(new ModelLangCode("인도네시아어", "id"));
        this.langCodes.add(new ModelLangCode("태국어", "th"));
        this.langCodes.add(new ModelLangCode("독일어", "de"));
        this.langCodes.add(new ModelLangCode("러시아어", "ru"));
        this.langCodes.add(new ModelLangCode("스페인어", "es"));
        this.langCodes.add(new ModelLangCode("이탈리아어", "it"));
        this.langCodes.add(new ModelLangCode("프랑스어", "fr"));
    }

    @Override
    protected String translate(String str, String source, String target){
        if (source.equals(target))
            return str;

        String url = "https://openapi.naver.com/v1/papago/n2mt";
        String query = "?source=" + source +
                "&target=" + target +
                "&text=" + str;

        // Naver API info
        NaverApiInfo naverApiInfo = apiInfoRepository.findAll().stream().findFirst().orElseThrow(NaverApiInfoNotExistException::new);
        String clientId = naverApiInfo.getClientId();
        String clientSecret = naverApiInfo.getClientSecret();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        // request
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try{
            // send
            ResponseEntity<ResponseNaverLangTranslate> response =
                    restTemplate.postForEntity(url + query, request, ResponseNaverLangTranslate.class);
            // response
            return Objects.requireNonNull(response.getBody()).getResult();
        } catch(RestClientException e){
            throw new ApiFailedException();
        } catch(NullPointerException e){
            throw new NullPointerException();
        }
    }

    @Override
    protected String detectLang(String str){
        String url = "https://openapi.naver.com/v1/papago/detectLangs";
        String query = "?query=" + str;

        // Naver API info
        NaverApiInfo naverApiInfo = apiInfoRepository.findAll().stream().findFirst().orElseThrow(NaverApiInfoNotExistException::new);
        String clientId = naverApiInfo.getClientId();
        String clientSecret = naverApiInfo.getClientSecret();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        // request
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try{
            // send
            ResponseEntity<ResponseNaverLangDetect> response =
                    restTemplate.postForEntity(url + query, request, ResponseNaverLangDetect.class);

            // response
            return Objects.requireNonNull(response.getBody()).getLangCode();
        } catch(HttpClientErrorException e){
            throw new ApiFailedException();
        } catch(NullPointerException e){
            throw new NullPointerException();
        }
    }

    @Override
    public Collection<ModelLangCode> getLangCode() {
        return this.langCodes;
    }
}
