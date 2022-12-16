package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.SinhVienNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISinhVienNewDao extends JpaRepository<SinhVienNew, Integer> {
    SinhVienNew getSinhVienNewByMaSinhVien(String maSv);


    Page<SinhVienNew> findSinhVienNewByTrangThai(Integer trangThai, Pageable pageable);

    @Query("SELECT sv.maSinhVien from DT_SinhVien as sv where sv.trangThai=:trangthai")
    Page<String> findByTrangThai(@Param("trangthai") Integer trangThai, Pageable pageable);


    @Query("select COUNT(sv.id) FROM DT_SinhVien  as sv where sv.trangThai=:trangthai")
    Integer countSinhVienNewByStatus(@Param("trangthai") Integer trangThai);
}
