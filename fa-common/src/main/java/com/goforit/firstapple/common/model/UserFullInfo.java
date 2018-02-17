package com.goforit.firstapple.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.BeanUtils;

/**
 * Created by goforit on 17/4/10.
 */
public class UserFullInfo extends UserInfo {

    /**
     * 用户真实信息
     */
    private UserRealInformation realInformation;

    public static UserFullInfo buildFullInfo(UserInfo userInfo,UserRealInformation realInformation){

        UserFullInfo fullInfo=new UserFullInfo();

        BeanUtils.copyProperties(userInfo,fullInfo);

        fullInfo.setRealInformation(realInformation);

        return fullInfo;
    }

    public UserRealInformation getRealInformation() {
        return realInformation;
    }

    public void setRealInformation(UserRealInformation realInformation) {
        this.realInformation = realInformation;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
