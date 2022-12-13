package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.layer.application.domain.dao.last.IDTTongKetDotDao;
import com.huce.doantotnghiep.layer.application.domain.dao.last.IDiemNewDao;
import com.huce.doantotnghiep.layer.application.domain.dao.last.IDotDao;
import com.huce.doantotnghiep.layer.application.domain.dao.last.ISinhVienNewDao;
import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareTest;
import com.huce.doantotnghiep.layer.application.domain.dto.mapper.SinhVienMapper;
import com.huce.doantotnghiep.layer.application.domain.entity.last.DTTongKetDot;
import com.huce.doantotnghiep.layer.application.domain.entity.last.DiemNew;
import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;
import com.huce.doantotnghiep.layer.application.service.ISinhVienNewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SinhVienNewService implements ISinhVienNewService {
    //    private final IDiemNewDao iDiemOldDao;
    private final IDotDao iDotDao;
    private final ISinhVienNewDao iSinhVienNewOldDao;
    private final IDiemNewDao iDiemNewDao;

    private final IDTTongKetDotDao idtTongKetDotDao;

    private final SinhVienMapper sinhVienMapper;

    public SinhVienNewService(IDotDao iDotDao, ISinhVienNewDao iSinhVienNewOldDao, IDiemNewDao iDiemNewDao, IDTTongKetDotDao idtTongKetDotDao, SinhVienMapper sinhVienMapper) {
        this.iDotDao = iDotDao;
        this.iSinhVienNewOldDao = iSinhVienNewOldDao;
        this.iDiemNewDao = iDiemNewDao;
        this.idtTongKetDotDao = idtTongKetDotDao;
        this.sinhVienMapper = sinhVienMapper;
    }


    @Override
    public SinhVienCompareTest getSinhView(String maSV) {
        SinhVienNew sinhVienNew = iSinhVienNewOldDao.getSinhVienNewByMaSinhVien(maSV);
        if (sinhVienNew == null) return null;
        List<DiemNew> diemNews = iDiemNewDao.getAllByIdSinhVien(sinhVienNew.getId());
        return sinhVienMapper.createSinhVienCompareTestNew(sinhVienNew,diemNews);
    }
}
