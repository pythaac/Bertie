package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.NaverApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNaverApiInfoRepository extends JpaRepository<NaverApiInfo, String>, NaverApiInfoRepository {
}
