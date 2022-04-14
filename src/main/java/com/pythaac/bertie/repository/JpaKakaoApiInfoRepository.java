package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.KakaoApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaKakaoApiInfoRepository extends JpaRepository<KakaoApiInfo, String>, KakaoApiInfoRepository {
}
