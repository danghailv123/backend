package com.huce.doantotnghiep.layer.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowCompareDTO {

    private String year;

    private String hk;

    @JsonProperty("semester")
    private List<CompareSubjectModel> semester = new ArrayList<>();

    @JsonProperty("semester_summary")
    private CompareSummaryModel semesterSummary = new CompareSummaryModel();

    public void sort() {
        List<CompareSubjectModel> arrFalse = new ArrayList<>();
        List<CompareSubjectModel> arrTrue = new ArrayList<>();
        for (CompareSubjectModel compareSubjectModel : semester) {
            if (compareSubjectModel.getIsCompare()) arrTrue.add(compareSubjectModel);
            else arrFalse.add(compareSubjectModel);
        }
        semester = new ArrayList<>();
        semester.addAll(arrFalse);
        semester.addAll(arrTrue);
    }
}
