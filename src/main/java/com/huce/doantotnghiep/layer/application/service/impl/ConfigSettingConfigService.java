package com.huce.doantotnghiep.layer.application.service.impl;

import com.huce.doantotnghiep.layer.application.domain.dao.process.IConfigSettingDao;
import com.huce.doantotnghiep.layer.application.domain.entity.process.ConfigSetting;
import com.huce.doantotnghiep.layer.application.service.IConfigSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigSettingConfigService implements IConfigSettingService {
    private final IConfigSettingDao iConfigSettingDao;

    public ConfigSettingConfigService(IConfigSettingDao iConfigSettingDao) {
        this.iConfigSettingDao = iConfigSettingDao;
    }


    @Override
    public ConfigSetting save(ConfigSetting configSetting) {
        return iConfigSettingDao.save(configSetting);
    }

    @Override
    public ConfigSetting getConfigSetting(Integer id) {
        return iConfigSettingDao.findConfigSettingById(id);
    }

    @Override
    public ConfigSetting updateConfigSetting(ConfigSetting configSetting) {
        return iConfigSettingDao.save(configSetting);
    }


}
