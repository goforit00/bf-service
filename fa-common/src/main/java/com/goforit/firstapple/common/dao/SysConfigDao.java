package com.goforit.firstapple.common.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 18/2/9.
 */
public interface SysConfigDao {
    
    String getValueByAppNameAndKey(@Param("appName") String appName,@Param("key") String key);
}
