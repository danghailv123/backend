package com.huce.doantotnghiep.layer.application.domain.dao.process;

import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfigSettingDao extends JpaRepository<ConfigSetting,Integer> {

    ConfigSetting findConfigSettingById(Integer id);
}
