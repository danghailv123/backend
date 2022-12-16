package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.dto.ListHistoryShow;
import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import com.huce.doantotnghiep.utility.response.ResultResponse;

public interface IHistorySettingService {
    HistoryProcess create(HistoryProcess historyProcess);

    ListHistoryShow getList(Integer page, Integer limit, String keyword);
}
