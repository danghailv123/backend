package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SinhVienCompareModel {
    @JsonProperty("mssv")
    private String mssv;

    @JsonProperty("info")
    private InfoCompare infoCompare;

    @JsonProperty("overview")
    private Overview overview;
//    @JsonProperty("name")
//    private String name;
//
//    @JsonProperty("class")
//    private String nameClass;
//
//    @JsonProperty("birthday")
//    private String birthday;
//
//    @JsonProperty("email")
//    private String email;
//
//    @JsonProperty("gender")
//    private String gender;

    @JsonProperty("compare")
    private Map<Integer, Map<Integer, ShowCompareModel>> compare = new HashMap<>();

    @JsonProperty("note")
    private String note;

    @JsonProperty("is_compare")
    private Boolean isCompare = true;
}
