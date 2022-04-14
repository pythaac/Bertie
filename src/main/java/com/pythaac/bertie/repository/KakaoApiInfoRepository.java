package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.KakaoApiInfo;

import java.util.Collection;

public interface KakaoApiInfoRepository {
    Collection<KakaoApiInfo> findAll();
}
