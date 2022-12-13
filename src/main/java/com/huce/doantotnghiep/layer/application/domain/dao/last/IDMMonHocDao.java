package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.DMMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDMMonHocDao extends JpaRepository<DMMonHoc, Integer> {
    DMMonHoc getDMMonHocById(Integer maMonHoc);

    DMMonHoc getDMMonHocByMaMonHoc(String maMonHoc);
}
