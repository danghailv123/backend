package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.layer.application.domain.dao.old.IDiemOldDao;
import com.huce.doantotnghiep.layer.application.domain.dao.old.IDiemTKHKDao;
import com.huce.doantotnghiep.layer.application.domain.dao.old.ISinhVienOldDao;
import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareTest;
import com.huce.doantotnghiep.layer.application.domain.dto.mapper.SinhVienMapper;
import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemOld;
import com.huce.doantotnghiep.layer.application.domain.entity.old.SinhVienOld;
import com.huce.doantotnghiep.layer.application.service.ISinhVienOldService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SinhVienOldService implements ISinhVienOldService {
    private final ISinhVienOldDao iSinhVienOldDao;

    private final SinhVienMapper sinhVienMapper;

    private final IDiemTKHKDao iDiemTKHKDao;
    private final IDiemOldDao iDiemOldDao;

    public SinhVienOldService(ISinhVienOldDao iSinhVienOldDao, SinhVienMapper sinhVienMapper, IDiemTKHKDao iDiemTKHKDao, IDiemOldDao iDiemOldDao) {
        this.iSinhVienOldDao = iSinhVienOldDao;
        this.sinhVienMapper = sinhVienMapper;
        this.iDiemTKHKDao = iDiemTKHKDao;
        this.iDiemOldDao = iDiemOldDao;
    }


    @Override
    public SinhVienCompareTest getSinhView(String maSV) {
        SinhVienOld sinhVienOld = iSinhVienOldDao.getSinhVienOldByMaSV(maSV);
        if (sinhVienOld == null) {
            return null;
        }
        List<DiemOld> diemOlds = iDiemOldDao.getAllByMaSV(maSV);
        return sinhVienMapper.createSinhVienCompareTestOld(sinhVienOld, diemOlds);
    }
}
