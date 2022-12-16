package com.huce.doantotnghiep.layer.application.domain.dao.process;

import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProcessCompareDao extends JpaRepository<ProcessCompare, Integer> {


    @Query("SELECT COUNT (ps.id) from process_compare as ps where ps.jobId=:id")
    Integer countTotalError(@Param("id") Integer id);

    Page<ProcessCompare> getAllByJobIdAndMssvLike(Integer id,String mssv, Pageable pageable);
}
