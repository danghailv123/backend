package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryProcessDTO {
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_time")
    private Timestamp createdTime;

    @JsonProperty("status")
    private String status;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("total_error")
    private Integer totalError = 0;

}
