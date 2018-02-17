package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.dao.SysConfigDao;
import com.goforit.firstapple.core.service.DBConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 18/2/8.
 */

@Service
public class DBConfigServiceImpl implements DBConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public String getValueByAppNameAndKey(String appName, String key) {

        return sysConfigDao.getValueByAppNameAndKey(appName,key);
    }

    @Override
    public Integer getIntegerValueByAppNameAndKey(String appName, String key,Integer defaultValue) {

        String value=sysConfigDao.getValueByAppNameAndKey(appName,key);
        if(StringUtils.isBlank(value)){
            return defaultValue;
        }

        return Integer.valueOf(value);
    }


}
