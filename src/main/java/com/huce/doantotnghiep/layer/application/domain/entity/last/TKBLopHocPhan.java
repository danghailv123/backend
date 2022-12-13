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
@Entity(name = "TKB_LopHocPhan")
public class TKBLopHocPhan {

    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IDDot")
    private Integer idDot;

    @Column(name = "IDMonHoc")
    private Integer idMonHoc;

    @Column(name = "MaLopHocPhan")
    private String maLopHocPhan;

    @Column(name = "LopDuKien")
    private String lopDuKien;
}
