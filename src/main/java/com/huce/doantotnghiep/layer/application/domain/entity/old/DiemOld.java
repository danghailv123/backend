package com.huce.doantotnghiep.layer.application.domain.entity.old;

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
@Entity(name = "Diem")
public class DiemOld {

    // năm học và học kỳ
    @Column(name = "NhHk")
    private String nhHk;

    @Column(name = "Madksv")
    @Id
    private String maDksv;

    // mã số sinh viên
    @Column(name = "MaSV")
    private String maSV;

    // mã môn học
    @Column(name = "MaMH")
    private String maMH;

    @Column(name = "MaNH")
    private String maNH;

    @Column(name = "Mato")
    private String maTo;

    @Column(name = "DiemP1")
    private String diemP1;

    @Column(name = "DiemP2")
    private String diemP2;

    @Column(name = "DiemP3")
    private String diemP3;

    @Column(name = "DiemQ2")
    private String diemQ2;

    @Column(name = "DiemQ3")
    private String diemQ3;

    @Column(name = "DiemKT1")
    private String diemKT1;

    @Column(name = "DiemTH")
    private String diemTH;

    @Column(name = "DiemTL2")
    private String diemTL2;

    @Column(name = "DiemTL3")
    private String diemLT3;

    // hỏi thầy trường này
    @Column(name = "PHTR1")
    private String PHTR1;

    @Column(name = "PHTRThi")
    private String PHTRThi;

    // điểm giữa kỳ
    @Column(name = "DiemTK1")
    private String diemTK1;

    // điểm thi kết thúc
    @Column(name = "DiemTK")
    private String diemTK;

    // điểm quy sang thang điểm 4
    @Column(name = "Diems")
    private String diems;

    @Column(name = "DiemTK1S")
    private String diemTK1S;

    @Column(name = "DiemCH1")
    private String diemCH1;

    @Column(name = "DiemCH")
    private String diemCH;

    @Column(name = "Dat1")
    private String dat1;

    @Column(name = "Dat")
    private String dat;

    @Column(name = "DiemP4")
    private String diemP4;

    @Column(name = "DiemP5")
    private String diemP5;

    @Column(name = "DiemP6")
    private String diemP6;

    @Column(name = "DiemP7")
    private String diemP7;

    @Column(name = "DiemP8")
    private String diemP8;

    @Column(name = "DiemP9")
    private String diemP9;

    @Column(name = "DiemQ4")
    private String diemQ4;

    @Column(name = "DiemQ5")
    private String diemQ5;

    @Column(name = "DiemQ6")
    private String diemQ6;

    @Column(name = "DiemQ7")
    private String diemQ7;

    @Column(name = "DiemQ8")
    private String diemQ8;

    @Column(name = "DiemQ9")
    private String diemQ9;

    @Column(name = "DiemKT2")
    private String diemKT2;

    @Column(name = "DiemKT3")
    private String diemKT3;

    @Column(name = "DiemKT4")
    private String diemKT4;

    @Column(name = "DiemKT5")
    private String diemKT5;

    @Column(name = "DiemKT6")
    private String diemKT6;

    @Column(name = "DiemKT7")
    private String diemKT7;

    @Column(name = "DiemKT8")
    private String diemKT8;

    @Column(name = "DiemKT9")
    private String diemKT9;

    @Column(name = "DiemQ1")
    private String diemQ1;

    @Column(name = "phtr2")
    private String phtr2;

    @Column(name = "phtrth")
    private String phtrth;

    @Column(name = "phtr3")
    private String phtr3;

    @Column(name = "diemkt11")
    private String diemkt11;

    @Column(name = "diemkt12")
    private String diemkt12;

    @Column(name = "diemth1")
    private String diemth1;

    @Column(name = "diemth2")
    private String diemth2;

    @Column(name = "diemtl21")
    private String diemtl21;

    @Column(name = "diemtl22")
    private String diemtl22;

    @Column(name = "NgayHoc")
    private String ngayHoc;

    @Column(name = "Phtr4")
    private String phtr4;

    @Column(name = "DiemKT13")
    private String diemKT13;

    @Column(name = "DiemBT11")
    private String diemBT11;

    @Column(name = "DiemBT12")
    private String diemBT12;

    @Column(name = "GCDiem")
    private String gCDiem;

    @Column(name = "diemtk3")
    private String diemtk3;
    @Column(name = "f_loaidkmh")
    private Integer fLoaiDKMH;

    @Column(name = "SVNganh")
    private Integer svNganh;

}
