package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.MerchantCompanyInfo;
import com.goforit.firstapple.common.model.MerchantManagerInfo;
import com.sun.tools.javac.util.List;

/**
 * Created by goforit on 17/4/20.
 */
public interface MerchantCompanyService {

    /**
     * 创建公司
     * @param merchantManagerInfo
     * @return
     */
    MerchantCompanyInfo createCompany(MerchantManagerInfo merchantManagerInfo);

    /**
     * 查询
     * @param managerCode
     * @return
     */
    List<MerchantManagerInfo> findByManagerCode(String managerCode);
}
