package com.huce.doantotnghiep.layer.application.domain.entity.last;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "DT_SinhVien")
public class SinhVienNew {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "MaSinhVien")
    private String maSinhVien;

    @Column(name = "HoDem")
    private String hoDem;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @Column(name = "NgaySinh2")
    private String ngaySinh;

    @Column(name = "NoiSinh")
    private Integer noiSinh;

    @Column(name = "NoiSinh_IDHuyen")
    private Integer noiSinhIDHuyen;

    @Column(name = "TonGiao")
    private String tonGiao;

    @Column(name = "TruongTotNghiep")
    private String truongTotNghiep;

    @Column(name = "IDTinh")
    private String idTinh;

    @Column(name = "NguyenQuan")
    private String nguyenQuan;

    @Column(name = "DiaChiThuongTru")
    private String diaChiThuongTru;

    @Column(name = "DiaChiLienLac")
    private String diaChiLienLac;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "IDCoSo")
    private Integer idCoSo;

    @Column(name = "IDHeDaoTao")
    private Integer idHeDaoTao;

    @Column(name = "IDLoaiHinhDT")
    private Integer idLoaiHinhDT;

    @Column(name = "IDKhoaHoc")
    private Integer idKhoaHoc;

    @Column(name = "IDNganh")
    private Integer idNganh;

    @Column(name = "IDLopHoc")
    private Integer idLopHoc;

    @Column(name = "SoCMND")
    private String soCMND;

    @Column(name = "QuocTich")
    private String quocTich;

    @Column(name = "Email")
    private String email;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "MaHoSo")
    private String maHoSo;

    @Column(name = "NgayNhapHoc")
    private String ngayNhapHoc;

    @Column(name = "SoTaiKhoan")
    private String soTaiKhoan;

    @Column(name = "NoiSinh_Text")
    private String noiSinhText;

}
