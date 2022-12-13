package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.DTTongKetDot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDTTongKetDotDao extends JpaRepository<DTTongKetDot, Integer> {
    List<DTTongKetDot> findAllDTTongKetDotByIdSinhVien(Integer idSV);

    DTTongKetDot findDTTongKetDotByIdSinhVienAndIdDot(Integer idSV,Integer idDot);
}
