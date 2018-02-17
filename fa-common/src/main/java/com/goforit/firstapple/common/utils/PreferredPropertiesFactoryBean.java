package com.goforit.firstapple.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by goforit on 17/3/31.
 */
public class PreferredPropertiesFactoryBean extends PropertiesLoaderSupport
        implements FactoryBean<Properties>, InitializingBean {

    private boolean singleton = true;
    private Properties singletonInstance;

    private Resource[] preferredLocations;

    private Resource[] backupLocations;

    public PreferredPropertiesFactoryBean() {}

    public final void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public final boolean isSingleton() {
        return this.singleton;
    }

    public final void afterPropertiesSet() throws IOException {
        boolean preferredAvailable = true;
        if(ArrayUtils.isEmpty(preferredLocations)) {
            preferredAvailable = false;
        } else {
            for (Resource res : preferredLocations) {
                if (!res.exists()) {
                    preferredAvailable = false;
                    break;
                }
            }
        }
        if(preferredAvailable) {
            setLocations(preferredLocations);
        } else {
            setLocations(backupLocations);
        }
        if (this.singleton) {
            this.singletonInstance = this.createProperties();
        }

    }

    public final Properties getObject() throws IOException {
        return this.singleton ? this.singletonInstance : this.createProperties();
    }

    public Class<Properties> getObjectType() {
        return Properties.class;
    }

    private Properties createProperties() throws IOException {
        return this.mergeProperties();
    }

    /**
     * 首选的配置，如果首选配置文件任何一个不存在，则使用备用的。
     * @param preferredLocations 首选的配置
     */
    public void setPreferredLocations(Resource[] preferredLocations) {
        this.preferredLocations = preferredLocations;
    }

    /**
     * 设置备用的配置，如果首选配置文件任何一个不存在，则使用备用的。
     * @param backupLocations 备用的配置
     */
    public void setBackupLocations(Resource[] backupLocations) {
        this.backupLocations = backupLocations;
    }
}
