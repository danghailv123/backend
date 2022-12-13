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
@Entity(name = "DM_LopHoc")
public class DMLopHoc {
    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "MaLopChu")
    private String maLopChu;

    @Column(name = "TenLop")
    private String tenLop;
}
