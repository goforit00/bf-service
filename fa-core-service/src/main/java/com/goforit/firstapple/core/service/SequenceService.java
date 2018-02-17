package com.goforit.firstapple.core.service;

/**
 * Created by goforit on 17/4/19.
 */
public interface SequenceService {

    /**
     * 生产公司编号
     * @return
     */
    String generateCompanyCode();

    /**
     * 生成商家编号
     * @return
     */
    String generateShopCode();

    /**
     * 生产商家管理者编号
     * @return
     */
    String generateMerchantManagerCode();
}
