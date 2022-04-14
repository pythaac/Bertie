package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.NaverApiInfo;
import com.pythaac.bertie.dto.ResponseNaverLangDetect;
import com.pythaac.bertie.dto.ResponseNaverLangTranslate;
import com.pythaac.bertie.exception.ApiFailedException;
import com.pythaac.bertie.exception.NaverApiInfoNotExistException;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class NaverLanguageService extends LanguageService{
    private final RestTemplate restTemplate;
    private final NaverApiInfoRepository apiInfoRepository;

    @Autowired
    public NaverLanguageService(NaverApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder.build();
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

//    enum LANG{
//        ko, en, ja, zh-CN, zh-TW, vi, id, th, de, ru, es, it, fr
//    }
}
