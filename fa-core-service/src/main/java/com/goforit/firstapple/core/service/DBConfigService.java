package com.goforit.firstapple.core.service;

/**
 * Created by goforit on 18/2/4.
 */
public interface DBConfigService {

    /**
     * 通过 appName和key获取 value
     * @param appName
     * @param key
     * @return
     */
    String getValueByAppNameAndKey(String appName, String key);

    /**
     * 通过 appName,Key 获取 int值,不存在在返回 defaultValue
     * @param appName
     * @param key
     * @param defaultValue
     * @return
     */
    Integer getIntegerValueByAppNameAndKey(String appName, String key, Integer defaultValue);
}
