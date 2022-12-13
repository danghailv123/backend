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
public class CompareSummary {
    @JsonProperty("year")
    private String year;
    @JsonProperty("hk")
    private String hk;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("score_4")
    private String score4;

    @JsonProperty("summary_all")
    private String summaryAll;

    @JsonProperty("score_4_all")
    private String score4All;

    @JsonProperty("tcck")
    private String tongSoTCHK;

    @JsonProperty("tcc")
    private String tongSoTCTL;
}
