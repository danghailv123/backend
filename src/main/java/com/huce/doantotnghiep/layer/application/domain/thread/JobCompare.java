package com.huce.doantotnghiep.layer.application.domain.thread;

import com.huce.doantotnghiep.layer.application.domain.dao.process.IProcessCompareDao;
import com.huce.doantotnghiep.layer.application.domain.dto.CompareShow;
import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import com.huce.doantotnghiep.layer.application.service.ICompareSinhVien;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class JobCompare implements Callable<List<ProcessCompare>> {
    private List<String> listSinhVien;
    private ICompareSinhVien compareSinhVien;
    private Integer page;

    private IProcessCompareDao iProcessCompareDao;

    public JobCompare(List<String> listSinhVien, ICompareSinhVien compareSinhVien, Integer page, IProcessCompareDao iProcessCompareDao) {
        this.listSinhVien = listSinhVien;
        this.compareSinhVien = compareSinhVien;
        this.page = page;
        this.iProcessCompareDao = iProcessCompareDao;
    }

    @Override
    public List<ProcessCompare> call() throws Exception {
        log.info(Thread.currentThread().getName() + " start job:" + page);
        List<ProcessCompare> processCompares = new ArrayList<>();
        Integer i = 1;
        for (String sinhVienNew : listSinhVien) {
            try {
                //TODO
                ProcessCompare processCompare = new ProcessCompare();
                CompareShow compareShow = compareSinhVien.isCompareSinhVien(sinhVienNew);
                processCompare.setIsCompare(compareShow.isCompare());
                processCompare.setMssv(sinhVienNew);
                processCompare.setName(compareShow.tile);

                processCompares.add(iProcessCompareDao.save(processCompare));
            } catch (Exception exception) {
                log.error(sinhVienNew);
            }
            i++;
        }
        log.info(Thread.currentThread().getName() + " end job:" + page);
        return processCompares;
    }
}
