package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.TKBLopHocPhan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITKBLopHocPhanDao extends JpaRepository<TKBLopHocPhan, Integer> {
    TKBLopHocPhan getTKBLopHocPhanById(Integer id);
}
