package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.dto.ListCompareShow;

public interface IProcessCompareService {
    ListCompareShow getList(Integer id, Integer page, Integer size, String keyword);
}
