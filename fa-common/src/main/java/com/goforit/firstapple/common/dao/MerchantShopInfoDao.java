package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.MerchantShopInfo;

import java.util.List;

/**
 * Created by goforit on 17/4/28.
 */
public interface MerchantShopInfoDao {

    //~~~~~ Modified Method ~~~~~

    int create(MerchantShopInfo merchantShopInfo);

    int updateByShopCode(MerchantShopInfo merchantShopInfo);



    //~~~~~ Query Method ~~~~~

    List<MerchantShopInfo> findShopsByShopCodes(List<String> shopCodes);

    MerchantShopInfo findByShopCode(String shopCode);


}
