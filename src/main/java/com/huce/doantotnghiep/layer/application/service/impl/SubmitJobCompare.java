package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.config.Constants;
import com.huce.doantotnghiep.layer.application.domain.dao.last.ISinhVienNewDao;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IConfigSettingDao;
import com.huce.doantotnghiep.layer.application.domain.dao.process.IProcessCompareDao;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import com.huce.doantotnghiep.layer.application.domain.thread.JobCompare;
import com.huce.doantotnghiep.layer.application.service.ICompareSinhVien;
import com.huce.doantotnghiep.layer.application.service.IGoogleSheetService;
import com.huce.doantotnghiep.layer.application.service.ISubmitJobCompare;
import com.huce.doantotnghiep.utility.tasks.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SubmitJobCompare implements ISubmitJobCompare {
    private final ISinhVienNewDao iSinhVienNewDao;
    private final ICompareSinhVien compareSinhVien;
    private final ExecutorService executor;

    private final IConfigSettingDao iConfigSettingDao;
    private final IProcessCompareDao iProcessCompareDao;

    public SubmitJobCompare(ISinhVienNewDao iSinhVienNewDao, ICompareSinhVien compareSinhVien, IConfigSettingDao iConfigSettingDao, IProcessCompareDao iProcessCompareDao) {
        this.iSinhVienNewDao = iSinhVienNewDao;
        this.compareSinhVien = compareSinhVien;
        this.iConfigSettingDao = iConfigSettingDao;
        this.iProcessCompareDao = iProcessCompareDao;
        executor = ThreadPool.builder()
                .setCoreSize(5)
                .setQueueSize(0)
                .setNamePrefix("processing compare")
                .setDaemon(true)
                .build();
    }

    @Override
    public void jobFile(List<String> data) {
        iProcessCompareDao.deleteAll();
        startJobFile(data);
        ConfigSetting configSetting = iConfigSettingDao.findConfigSettingById(1);
        configSetting.setStatus(0);
        iConfigSettingDao.save(configSetting);
    }

    @Override
    public void jobDatabase() {
        iProcessCompareDao.deleteAll();
        startJobDatabase();

    }

    public void startJobFile(List<String> data) {

        int size = data.size();
        int page = 0;
        while (true) {
            List<String> listProcess = data.subList(page * 200, Math.min((page + 1) * 200, size));
            JobCompare jobCompare = new JobCompare(listProcess, compareSinhVien, page, iProcessCompareDao);
            Constants.jobSubmit.add(executor.submit(jobCompare));
            if (listProcess.isEmpty() || size < (page + 1) * 200) return;
            page++;
        }
    }

    public void startJobDatabase() {
        int page = 0;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        while (true) {
            Pageable pageable = PageRequest.of(page, 1000, sort);
            Page<String> sinhVienNews = iSinhVienNewDao.findByTrangThai(1, pageable);
            if (sinhVienNews.isEmpty()) return;
            JobCompare jobCompare = new JobCompare(sinhVienNews.toList(), compareSinhVien, page, iProcessCompareDao);
            Constants.jobSubmit.add(executor.submit(jobCompare));
            page++;
        }
    }


}
