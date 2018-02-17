package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.UserToken;
import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 17/4/1.
 */
public interface UserTokenDao {

    //~~~~~ Modified Method ~~~~~
    int created(UserToken token);

    int update(UserToken token);

    //~~~~~ Query Method ~~~~~
    UserToken findByNickName(@Param("userNickName") String userNickName);
}
