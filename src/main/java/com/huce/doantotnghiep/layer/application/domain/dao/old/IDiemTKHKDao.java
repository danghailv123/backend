package com.huce.doantotnghiep.layer.application.domain.dao.old;

import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemTKHK;
import com.huce.doantotnghiep.layer.application.domain.model.DiemTKHKKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiemTKHKDao extends JpaRepository<DiemTKHK, DiemTKHKKey> {
    List<DiemTKHK> findAllDiemTKHKByMaSV(String masv);

    DiemTKHK findDiemTKHKByMaSVAndNhhk(String masv, String nhhk);
}
