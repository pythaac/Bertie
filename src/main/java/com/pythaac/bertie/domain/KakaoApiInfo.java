package com.pythaac.bertie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KakaoApiInfo {
    @Id
    @Column(name = "rest_api_key")
    String rest_api_key;

    public KakaoApiInfo(String rest_api_key) {
        this.rest_api_key = rest_api_key;
    }

    public KakaoApiInfo() {
    }

    public String getRest_api_key() {
        return rest_api_key;
    }

    public void setRest_api_key(String rest_api_key) {
        this.rest_api_key = rest_api_key;
    }
}
