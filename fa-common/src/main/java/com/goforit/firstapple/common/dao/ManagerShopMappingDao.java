package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.ManagerShopMappingInfo;

import java.util.List;

/**
 * Created by goforit on 17/4/28.
 */
public interface ManagerShopMappingDao {

    //~~~~~ Modified Method ~~~~~

    int create(ManagerShopMappingInfo managerShopMappingInfo);

    //~~~~~ Query Method ~~~~~
    List<ManagerShopMappingInfo> findByManagerCode(String managerCode);

}
