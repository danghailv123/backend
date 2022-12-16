package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IHistoryProcessDao;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IProcessCompareDao;
import com.huce.doantotnghiep.layer.application.domain.dto.HistoryProcessDTO;
import com.huce.doantotnghiep.layer.application.domain.dto.ListHistoryShow;
import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import com.huce.doantotnghiep.layer.application.service.IHistorySettingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistorySettingService implements IHistorySettingService {

    private final IHistoryProcessDao historyProcessDao;

    private final IProcessCompareDao iProcessCompareDao;

    public HistorySettingService(IHistoryProcessDao historyProcessDao, IProcessCompareDao iProcessCompareDao) {
        this.historyProcessDao = historyProcessDao;
        this.iProcessCompareDao = iProcessCompareDao;
    }

    @Override
    public HistoryProcess create(HistoryProcess historyProcess) {
        return historyProcessDao.save(historyProcess);
    }

    @Override
    public ListHistoryShow getList(Integer page, Integer limit, String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, limit, sort);
        ListHistoryShow resultResponse = new ListHistoryShow();
        Page<HistoryProcess> historyProcesses = historyProcessDao.getAllByNameLike("%" + keyword + "%", pageable);
        resultResponse.setPage(page);
        resultResponse.setPage(page);
        resultResponse.setStartPage(0);
        int total = (int) historyProcesses.getTotalElements();
        resultResponse.setTotal(total);
        int totalPage = total % limit == 0 ? total / limit - 1 : total / limit;
        resultResponse.setEndPage(totalPage);
        List<HistoryProcessDTO> historyProcessDTOS = new ArrayList<>();
        for (HistoryProcess historyProcess : historyProcesses) {
            HistoryProcessDTO historyProcessDTO = new HistoryProcessDTO();
            historyProcessDTO.setId(historyProcess.getId());
            historyProcessDTO.setName(historyProcess.getName());
            historyProcessDTO.setTotalError(iProcessCompareDao.countTotalError(historyProcess.getId()));
            historyProcessDTO.setTotal(historyProcess.getTotal());
            historyProcessDTO.setCreatedTime(new Timestamp(historyProcess.getCreatedTime()));
            historyProcessDTO.setStatus(Constants.STATUS.get(historyProcess.getStatus()));
            historyProcessDTOS.add(historyProcessDTO);
        }
        resultResponse.setHistoryProcessDTOS(historyProcessDTOS);
        return resultResponse;
    }
}
