package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.UserRealInformation;

/**
 * Created by goforit on 17/4/10.
 */
public interface UserRealInformationDao {

    //~~~~~ Modification Method ~~~~~
    int create(UserRealInformation userRealInformation);

    int update(UserRealInformation userRealInformation);


    //~~~~~ Query Method ~~~~~

    UserRealInformation getByName(String nickName);
}
