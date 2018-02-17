package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.MerchantManagerInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 17/4/19.
 */
public interface MerchantManagerInfoDao {

    //~~~~~ Modification Method~~~~~
    int create(MerchantManagerInfo merchantManagerInfo);

    /**
     * 不修改 密码
     * @param merchantManagerInfo
     * @return
     */
    int updateUser(MerchantManagerInfo merchantManagerInfo);


    //~~~~~ Query Method ~~~~~
    MerchantManagerInfo getByEmail(@Param("email") String email);

    MerchantManagerInfo getByManagerCode(@Param("managerCode") String managerCode);

}
