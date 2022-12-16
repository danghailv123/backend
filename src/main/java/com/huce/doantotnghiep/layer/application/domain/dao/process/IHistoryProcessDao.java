package com.huce.doantotnghiep.layer.application.domain.dao.process;

import com.huce.doantotnghiep.layer.application.domain.entity.process.HistoryProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoryProcessDao extends JpaRepository<HistoryProcess,Integer> {
    Page<HistoryProcess> getAllByNameLike(String name, Pageable pageable);
    HistoryProcess getHistoryProcessById(Integer id);
}
