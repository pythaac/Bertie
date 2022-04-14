package com.pythaac.bertie.service.LangaugeServices;

import com.pythaac.bertie.domain.KakaoApiInfo;
import com.pythaac.bertie.domain.NaverApiInfo;
import com.pythaac.bertie.dto.ModelLangCode;
import com.pythaac.bertie.dto.ResponseNaverLangDetect;
import com.pythaac.bertie.dto.ResponseNaverLangTranslate;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Objects;

public class KakaoLanguageService extends LanguageService {
    private final RestTemplate restTemplate;
    private final KakaoApiInfoRepository apiInfoRepository;

    public KakaoLanguageService(KakaoApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder.build();
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
        return null;
    }
}
