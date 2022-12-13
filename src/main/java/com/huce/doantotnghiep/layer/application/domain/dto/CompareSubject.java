package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompareSubject {
    @JsonProperty("mmh")
    private String maMonHoc;

    @JsonProperty("qt")
    private String qt;

    @JsonProperty("kt")
    private String kt;

    @JsonProperty("tc")
    private String tc;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("name_subject")
    private String nameSubject;

    @JsonProperty("group_code")
    private String groupCode;

    @JsonProperty("score_string")
    private String scoreString;

    @JsonProperty("score_4")
    private String score4;
}
