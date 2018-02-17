package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.MerchantManagerToken;
import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 17/4/19.
 */
public interface MerchantManagerTokenDao {

    //~~~~~ Modified Method ~~~~~
    int created(MerchantManagerToken token);

    int update(MerchantManagerToken token);

    //~~~~~ Query Method ~~~~~
    MerchantManagerToken findByManagerCode(@Param("managerCode") String managerCode);

}
