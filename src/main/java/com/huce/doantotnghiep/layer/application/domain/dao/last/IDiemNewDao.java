package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.DiemNew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDiemNewDao extends JpaRepository<DiemNew, Integer> {

    List<DiemNew> getAllByIdSinhVien(Integer idSv);
}
