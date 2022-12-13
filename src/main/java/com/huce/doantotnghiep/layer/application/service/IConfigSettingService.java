package com.huce.doantotnghiep.layer.application.service;

import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;

public interface IConfigSettingService {
    ConfigSetting save(ConfigSetting configSetting);

    ConfigSetting getConfigSetting(Integer id);

    ConfigSetting updateConfigSetting(ConfigSetting configSetting);
}
