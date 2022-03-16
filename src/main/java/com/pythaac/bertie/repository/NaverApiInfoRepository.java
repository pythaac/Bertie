package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.NaverApiInfo;

import java.util.Collection;

public interface NaverApiInfoRepository {
    Collection<NaverApiInfo> findAll();
}
