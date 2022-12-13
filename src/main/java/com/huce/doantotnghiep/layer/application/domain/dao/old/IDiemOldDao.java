package com.huce.doantotnghiep.layer.application.domain.dao.old;

import com.huce.doantotnghiep.layer.application.domain.entity.old.DiemOld;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiemOldDao extends JpaRepository<DiemOld, String> {
    List<DiemOld> getAllByMaSV(String maSV);
}
