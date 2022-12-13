package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.Dot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDotDao extends JpaRepository<Dot,Integer> {
    Dot getDotById(Integer id);
}
