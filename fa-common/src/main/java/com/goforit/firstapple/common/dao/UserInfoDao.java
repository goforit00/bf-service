package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 17/4/1.
 */
public interface UserInfoDao {

    //~~~~~ Modification Method~~~~~
    int create(UserInfo userInfo);

    /**
     * 不修改 密码
     * @param userInfo
     * @return
     */
    int updateUser(UserInfo userInfo);

    int updateModifiedTime(@Param("userNickName")String userNickName);

    //~~~~~ Query Method ~~~~~
    UserInfo getByName(@Param("userNickName") String userNickName);
}
