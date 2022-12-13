package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareTest;
import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemOld;
import com.huce.doantotnghiep.layer.application.domain.entity.old.SinhVienOld;

import java.util.List;

public interface ISinhVienOldService {
    SinhVienCompareTest getSinhView(String maSV);
}
