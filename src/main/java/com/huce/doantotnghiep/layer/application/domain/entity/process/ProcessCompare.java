package com.huce.doantotnghiep.layer.application.domain.entity.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "process_compare")
public class ProcessCompare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mssv")
    private String mssv;

    @Column(name = "title")
    private String title;

    @Column(name = "is_compare")
    private Boolean isCompare;

    @Column(name = "job_id")
    private Integer jobId;
}
