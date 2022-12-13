package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.dto.SinhVienCompareTest;
import com.huce.doantotnghiep.layer.application.domain.entity.last.DiemNew;
import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;

import java.util.List;

public interface ISinhVienNewService {
    SinhVienCompareTest getSinhView(String maSV);
}
