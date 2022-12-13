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
@Entity(name = "TKB_MonHoc")
public class TKBMonHoc {
    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IDLopHoc")
    private Integer idLopHoc;

    @Column(name = "MaHocPhan")
    private String maHocPhan;

    @Column(name = "MaMonHoc")
    private String maMonHoc;

    @Column(name = "TenMonHoc")
    private String tenMonHoc;

    @Column(name = "SoTinChi")
    private Integer soTinChi;
}
