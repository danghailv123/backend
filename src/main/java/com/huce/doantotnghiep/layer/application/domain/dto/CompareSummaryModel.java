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
public class CompareSummaryModel {
    @JsonProperty("info_old")
    private CompareSummary infoOld;

    @JsonProperty("info_new")
    private CompareSummary infoNew;

    @JsonProperty("is_compare")
    private Boolean isCompare = true;
}
