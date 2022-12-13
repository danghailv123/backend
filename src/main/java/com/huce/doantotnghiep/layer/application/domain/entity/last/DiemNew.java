package com.huce.doantotnghiep.layer.application.domain.entity.last;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "DT_KetQuaHocTapMonHoc")
public class DiemNew {
    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IDSinhVien")
    private Integer idSinhVien;

    @Column(name = "IDLopHocPhan")
    private Integer idLopHocPhan;

    @Column(name = "DiemChuyenCan1")
    private Float diemChuyenCan;

    @Column(name = "DiemThi")
    private Float diemThi;

    @Column(name = "DiemTongKet")
    private Float diemTongKet;

    @Column(name = "DiemTinChi")
    private Float diemTinChi;

    @Column(name = "DiemChu")
    private String diemChu;
}
