package com.huce.doantotnghiep.layer.application.domain.entity.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "SinhVien")
public class SinhVienOld {

    // mã sinh viên
    // từ mã sinh viên lấy được điểm từ bảng điểm
    //
    @Id
    @Column(name = "MaSV")
    private String maSV;

    // họ và tên của sinh viên
    @Column(name = "HoTenSV")
    private String hoTenSV;

    // mã lớp
    @Column(name = "Malop")
    private String maLop;

    // ngày sinh
    @Column(name = "NgaySinh")
    private String ngaySinh;

    @Column(name = "HeNC")
    private String heNC;

    @Column(name = "PtMGHP")
    private Integer ptMGHP;

    @Column(name = "SoTCMax")
    private Integer soTCMax;

    // email sinh viên 1
    @Column(name = "EmailSV1")
    private String emailSV1;

    // email sinh viên 2
    @Column(name = "EmailSV2")
    private String emailSV2;

    // số điện thoại sinh viên 1
    @Column(name = "TelSV1")
    private String telSV1;

    // số điện thoại sinh viên 2
    @Column(name = "TelSV2")
    private String telSV2;

    // giới tính của sinh viên
    @Column(name = "phai")
    private String phai;


    @Column(name = "hiendien")
    private String hienDien;

    @Column(name = "tento")
    private String tenTo;

    @Column(name = "SoTCMin")
    private Float soTCMin;

    @Column(name = "active")
    private String active;

    //số tài khoản ngân hàng
    @Column(name = "SoTKNH")
    private String soTKNH;

    @Column(name = "Bhythk")
    private Float bhythk;

    @Column(name = "Pdbhythk")
    private Float pdbhythk;

    @Column(name = "Nobhytcu")
    private Float nobhytcu;

    @Column(name = "dadonghk")
    private String dadonghk;

    @Column(name = "MaChNg")
    private String maChNg;

    @Column(name = "NgayGioChon")
    private Timestamp ngayGioChon;

    @Column(name = "MaNgth2")
    private String maNgth2;

    @Column(name = "MaChNg2")
    private String maChNg2;

    @Column(name = "ChoPhepNo")
    private Integer choPhepNo;

    @Column(name = "pdhocphihk")
    private String pdHocPhiHk;

    @Column(name = "f_indexsv")
    private Integer fIndexSv;

    @Column(name = "nohkcu")
    private Float nohkcu;

    @Column(name = "tenlopn2")
    private String tenLopn2;

    @Column(name = "sotcmax2")
    private Integer soTcMax2;

    @Column(name = "sotcmax1")
    private Integer soTcMax1;

    // Nơi sinh
    @Column(name = "noisinh")
    private String noiSinh;

    @Column(name = "TenIndex")
    private Integer tenIndex;

    @Column(name = "SoTaiKhoan")
    private String soTaiKhoan;

    @Column(name = "MaNganhTH2")
    private String maNganhTH2;

    @Column(name = "dkvuot")
    private String dkVuot;

    @Column(name = "tblogin")
    private String tbLogin;

    @Column(name = "DCThTr")
    private String dcthtr;

    @Column(name = "SoCMND")
    private String soCMND;

    @Column(name = "HoTenCha")
    private String hoTenCha;

    @Column(name = "NgheCha")
    private String ngheCha;

    @Column(name = "HoTenMe")
    private String hoTenMe;

    @Column(name = "NgheMe")
    private String ngheMe;

    @Column(name = "DiaChi1")
    private String diaChi1;

    @Column(name = "DiaChi2")
    private String diaChi2;

    @Column(name = "DiaChiGD1")
    private String diaCHiGD1;

    @Column(name = "DiaChiGD2")
    private String diaChiGD2;

    @Column(name = "DienThGD")
    private String dianThGD;

    @Column(name = "Email365")
    private String email365;

    @Column(name = "SoTienMGHP")
    private Float soTienMGHP;

    @Column(name = "NgayBDDongHocPhi")
    private String ngayBDDongHocPhi;

    @Column(name = "NgayKTDongHocPhi")
    private String ngayKTDongHocPhi;
}
