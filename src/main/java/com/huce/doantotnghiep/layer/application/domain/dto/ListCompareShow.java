package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCompareShow {
    @JsonProperty("compare")
    private List<ProcessCompare> compareShows;

    private Integer page;

    private Integer size;

    @JsonProperty("start_page")
    private Integer startPage;
    @JsonProperty("end_page")
    private Integer endPage;
    private Integer total;
}
