package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.TKBMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITKBMonHoc extends JpaRepository<TKBMonHoc, Integer> {
    TKBMonHoc getTKBMonHocById(Integer id);
}
