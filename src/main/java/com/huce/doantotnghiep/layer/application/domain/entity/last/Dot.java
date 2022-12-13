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
@Entity(name = "DM_Dot")
public class Dot {
    @Id
    @Column(name = "Id")
    private Integer id;

    @Column(name = "SoThuTu")
    private Integer soThuTu;

    @Column(name = "TenDot")
    private String tenDot;

    @Column(name = "IDNamHoc")
    private String idNamHoc;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "IsVisible")
    private Boolean isVisible;

}
