package com.huce.doantotnghiep.layer.application.domain.dao.old;

import com.huce.doantotnghiep.layer.application.domain.entity.old.SinhVienOld;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISinhVienOldDao extends JpaRepository<SinhVienOld,String> {
    SinhVienOld getSinhVienOldByMaSV(String maSv);
}
