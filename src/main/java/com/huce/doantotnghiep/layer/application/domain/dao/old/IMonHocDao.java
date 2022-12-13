package com.huce.doantotnghiep.layer.application.domain.dao.old;

import com.huce.doantotnghiep.layer.application.domain.entity.old.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMonHocDao extends JpaRepository<MonHoc, Integer> {
    MonHoc getMonHocByMaMH(String maMh);
}
