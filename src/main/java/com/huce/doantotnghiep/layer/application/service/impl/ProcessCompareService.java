package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IHistoryProcessDao;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IProcessCompareDao;
import com.huce.doantotnghiep.layer.application.domain.dto.HistoryProcessDTO;
import com.huce.doantotnghiep.layer.application.domain.dto.ListCompareShow;
import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import com.huce.doantotnghiep.layer.application.service.IProcessCompareService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ProcessCompareService implements IProcessCompareService {
    private final IProcessCompareDao iProcessCompareDao;


    private final IHistoryProcessDao historyProcessDao;

    public ProcessCompareService(IProcessCompareDao iProcessCompareDao, IHistoryProcessDao historyProcessDao) {
        this.iProcessCompareDao = iProcessCompareDao;
        this.historyProcessDao = historyProcessDao;
    }

    @Override
    public ListCompareShow getList(Integer id, Integer page, Integer size, String keyword) {
        HistoryProcess historyProcess = historyProcessDao.getHistoryProcessById(id);
        HistoryProcessDTO historyProcessDTO = new HistoryProcessDTO();
        historyProcessDTO.setId(historyProcess.getId());
        historyProcessDTO.setName(historyProcess.getName());
        historyProcessDTO.setTotalError(iProcessCompareDao.countTotalError(id));
        historyProcessDTO.setTotal(historyProcess.getTotal());
        historyProcessDTO.setCreatedTime(new Timestamp(historyProcess.getCreatedTime()));
        historyProcessDTO.setStatus(Constants.STATUS.get(historyProcess.getStatus()));


        Sort sort = Sort.by(Sort.Direction.ASC, "isCompare");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProcessCompare> processCompares = iProcessCompareDao.getAllByJobIdAndMssvLike(id, keyword + "%", pageable);
        ListCompareShow listCompareShow = new ListCompareShow();
        listCompareShow.setCompareShows(processCompares.getContent());
        listCompareShow.setSize(size);
        listCompareShow.setPage(page);
        listCompareShow.setStartPage(0);
        listCompareShow.setHistoryProcessDTO(historyProcessDTO);
        int total = (int) processCompares.getTotalElements();
        listCompareShow.setTotal(total);
        int totalPage = total % size == 0 ? total / size - 1 : total / size;
        listCompareShow.setEndPage(totalPage);

        return listCompareShow;
    }
}
