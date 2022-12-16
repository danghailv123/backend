package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.layer.application.domain.dao.process.IProcessCompareDao;
import com.huce.doantotnghiep.layer.application.domain.dto.ListCompareShow;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import com.huce.doantotnghiep.layer.application.service.IProcessCompareService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProcessCompareService implements IProcessCompareService {
    private final IProcessCompareDao iProcessCompareDao;

    public ProcessCompareService(IProcessCompareDao iProcessCompareDao) {
        this.iProcessCompareDao = iProcessCompareDao;
    }

    @Override
    public ListCompareShow getList(Integer id, Integer page, Integer size, String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "isCompare");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProcessCompare> processCompares = iProcessCompareDao.getAllByJobIdAndMssvLike(id,keyword+"%",pageable);
        ListCompareShow listCompareShow = new ListCompareShow();
        listCompareShow.setCompareShows(processCompares.getContent());
        listCompareShow.setSize(size);
        listCompareShow.setPage(page);
        listCompareShow.setStartPage(0);

        int total = (int) processCompares.getTotalElements();
        listCompareShow.setTotal(total);
        int totalPage = total % size == 0 ? total / size - 1 : total / size;
        listCompareShow.setEndPage(totalPage);

        return listCompareShow;
    }
}
