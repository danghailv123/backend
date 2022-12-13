package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SinhVienCompareModelDTO {
    @JsonProperty("mssv")
    private String mssv;

    @JsonProperty("overview")
    private Overview overview;

    @JsonProperty("info")
    private InfoCompare infoCompare;
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
    private List<ShowCompareDTO> compare = new ArrayList<>();

    @JsonProperty("note")
    private String note;

    @JsonProperty("is_compare")
    private Boolean isCompare = true;
}
