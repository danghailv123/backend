package com.huce.doantotnghiep.layer.application.domain.dao.process;

import com.huce.doantotnghiep.layer.application.domain.entity.process.ProcessCompare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IProcessCompareDao extends JpaRepository<ProcessCompare, Integer> {
//    @Query(
//            value = " truncate table process_compare ",
//            nativeQuery = true)
//    @Modifying
//    void truncateTable();
}
