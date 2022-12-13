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
@Entity(name = "MonHoc")
public class MonHoc {

    @Id
    @Column(name = "MaMH")
    private String maMH;

    @Column(name = "TenMH")
    private String name;

    @Column(name = "SoTC")
    private Float soTC;
}
