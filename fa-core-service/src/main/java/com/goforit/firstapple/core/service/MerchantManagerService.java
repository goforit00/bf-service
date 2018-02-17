package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.MerchantManagerInfo;
import com.goforit.firstapple.common.model.MerchantManagerToken;
import com.goforit.firstapple.common.model.params.MerchantManagerRegisterParams;

/**
 * Created by goforit on 17/4/18.
 */
public interface MerchantManagerService {


    MerchantManagerInfo register(MerchantManagerRegisterParams registerParams);

    /**
     * manager 登录
     * @param email
     * @param password
     * @return
     */
    MerchantManagerToken login(String email, String password);

    /**
     * 查询 manager
     * @param email
     * @return
     */
    MerchantManagerInfo findByEmail(String email);

    /**
     *
     * @param managerCode
     * @return
     */
    MerchantManagerInfo findByManagerCode(String managerCode);

    /**
     * 校验登录token
     * @param managerCode
     * @param token
     * @return
     */
    boolean checkManagerToken(String managerCode,String token);
}
