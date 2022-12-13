package com.huce.doantotnghiep.layer.application.domain.dao.last;

import com.huce.doantotnghiep.layer.application.domain.entity.last.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmailDao extends JpaRepository<Email, Integer> {

    Email getEmailByIdSinhVien(Integer maSv);
}
