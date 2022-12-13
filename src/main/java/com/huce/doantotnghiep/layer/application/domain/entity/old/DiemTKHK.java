package com.huce.doantotnghiep.layer.application.domain.entity.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.huce.doantotnghiep.layer.application.domain.model.DiemTKHKKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "DiemTKHK")
@IdClass(DiemTKHKKey.class)
public class DiemTKHK {
    @Id
    @Column(name = "NHHK")
    private String nhhk;

    @Id
    @Column(name = "MaSV")
    private String maSV;

    @Id
    @Column(name = "SVNganh")
    private String svNganh;

    @Column(name = "DTB")
    private String dtb;

    @Column(name = "DTBS")
    private String dtbs;

    @Column(name = "DTBTL")
    private String dtbtl;

    @Column(name = "DTBTLS")
    private String dtbtls;

    @Column(name = "TCDTHK")
    private String tcdthk;

    @Column(name = "TCDTTL")
    private String tcdttl;

}
