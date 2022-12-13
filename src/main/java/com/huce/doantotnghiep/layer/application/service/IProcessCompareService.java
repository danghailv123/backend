package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.dto.ListCompareShow;
import com.huce.doantotnghiep.utility.response.ResultResponse;

public interface IProcessCompareService {
    ListCompareShow getList(Integer page, Integer size);
}
