package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.DMLopHoc;
import com.huce.doantotnghiep.layer.application.domain.entity.last.DMMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDMLopHocDao extends JpaRepository<DMLopHoc,Integer> {
    DMLopHoc getDMLopHocById(Integer id);
}
