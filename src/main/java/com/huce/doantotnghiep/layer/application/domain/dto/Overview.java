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
public class Overview {
    @JsonProperty("total_subjects")
    Integer totalSubjects;

    @JsonProperty("difference_subjects")
    Integer differenceSubjects;

    @JsonProperty("total_hk")
    Integer totalHk;

    @JsonProperty("difference_hk")
    Integer differenceHk;
}
