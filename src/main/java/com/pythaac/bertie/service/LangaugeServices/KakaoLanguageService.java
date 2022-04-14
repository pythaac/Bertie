package com.pythaac.bertie.service.LangaugeServices;

import com.pythaac.bertie.domain.KakaoApiInfo;
import com.pythaac.bertie.domain.NaverApiInfo;
import com.pythaac.bertie.dto.*;
import com.pythaac.bertie.exception.ApiFailedException;
import com.pythaac.bertie.exception.KakaoApiInfoNotExistException;
import com.pythaac.bertie.exception.NaverApiInfoNotExistException;
import com.pythaac.bertie.repository.KakaoApiInfoRepository;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import com.pythaac.bertie.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class KakaoLanguageService extends LanguageService {
    private final RestTemplate restTemplate;
    private final KakaoApiInfoRepository apiInfoRepository;
    private final ArrayList<ModelLangCode> langCodes;

    public KakaoLanguageService(KakaoApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder.build();
        this.langCodes = new ArrayList<>();
        setLangCodes();
    }

    private void setLangCodes(){
        this.langCodes.add(new ModelLangCode("한국어", "kr"));
        this.langCodes.add(new ModelLangCode("영어", "en"));
        this.langCodes.add(new ModelLangCode("일본어", "jp"));
        this.langCodes.add(new ModelLangCode("중국어", "cn"));
        this.langCodes.add(new ModelLangCode("베트남어", "vi"));
        this.langCodes.add(new ModelLangCode("인도네시아어", "id"));
        this.langCodes.add(new ModelLangCode("아랍어", "ar"));
        this.langCodes.add(new ModelLangCode("뱅갈어", "bn"));
        this.langCodes.add(new ModelLangCode("독일어", "de"));
        this.langCodes.add(new ModelLangCode("스페인어", "es"));
        this.langCodes.add(new ModelLangCode("프랑스어", "fr"));
        this.langCodes.add(new ModelLangCode("힌디어", "hi"));
        this.langCodes.add(new ModelLangCode("이탈리아어", "it"));
        this.langCodes.add(new ModelLangCode("말레이시아어", "ms"));
        this.langCodes.add(new ModelLangCode("네덜란드어", "nl"));
        this.langCodes.add(new ModelLangCode("포르투갈어", "pt"));
        this.langCodes.add(new ModelLangCode("러시아어", "ru"));
        this.langCodes.add(new ModelLangCode("태국어", "th"));
        this.langCodes.add(new ModelLangCode("터키어", "tr"));
    }

    @Override
    protected String translate(String str, String source, String target){
        if (source.equals(target))
            return str;

        String url = "https://dapi.kakao.com/v2/translation/translate";
        String query = "?src_lang=" + source +
                "&target_lang=" + target +
                "&query=" + str;

        // Kakao API info
        KakaoApiInfo kakoApiInfo = apiInfoRepository.findAll().stream().findFirst().orElseThrow(KakaoApiInfoNotExistException::new);
        String authorization = "KakaoAK " + kakoApiInfo.getRest_api_key();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        // request
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try{
            // send
            ResponseEntity<ResponseKakaoLangTranslate> response =
                    restTemplate.exchange(url + query, HttpMethod.GET, request, ResponseKakaoLangTranslate.class);
            // response
            return Objects.requireNonNull(response.getBody()).getTranslatedText();
        } catch(RestClientException e){
            throw new ApiFailedException();
        } catch(NullPointerException e){
            throw new NullPointerException();
        }
    }

    @Override
    protected String detectLang(String str){
        String url = "https://dapi.kakao.com/v3/translation/language/detect";
        String query = "?query=" + str;

        // Kakao API info
        KakaoApiInfo kakoApiInfo = apiInfoRepository.findAll().stream().findFirst().orElseThrow(KakaoApiInfoNotExistException::new);
        String authorization = "KakaoAK " + kakoApiInfo.getRest_api_key();

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        // request
        HttpEntity<Object> request = new HttpEntity<>(headers);

        try{
            // send
            ResponseEntity<ResponseKakaoLangDetectWrapper> response =
                    restTemplate.exchange(url + query, HttpMethod.GET, request, ResponseKakaoLangDetectWrapper.class);

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
