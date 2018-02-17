package com.goforit.firstapple.core.service.impl;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.core.manager.SequenceManager;
import com.goforit.firstapple.core.service.SequenceService;

/**
 * Created by goforit on 17/4/19.
 */

@Service
public class SequenceServiceImpl implements SequenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceServiceImpl.class);

    private static final Integer GENERATE_RETRY_TIMES = 3;

    private static final Integer RANDOM_SHEEP_TIME = 50;

    @Autowired
    private SequenceManager sequenceManager;

    public String generateCompanyCode() {
        for (int i = 0; i < GENERATE_RETRY_TIMES; i++) {
            try {
                Long seq = sequenceManager.getSeq(FaSysConst.COMPANY_CODE_SEQ_NAME);
                return FaSysConst.COMPANY_CODE_PREFIX + String.format("%08d", seq);
            } catch (Exception e) {
                LOGGER.warn("获取company code 异常,重试次数[{}]", i, e);
                try {
                    Thread.sleep(RandomUtils.nextInt(0, RANDOM_SHEEP_TIME));
                } catch (InterruptedException ie) {
                    LOGGER.warn(ie.getMessage(), ie);
                }
            }
        }
        LOGGER.error("获取company code失败");
        return null;
    }

    public String generateShopCode() {
        for (int i = 0; i < GENERATE_RETRY_TIMES; i++) {
            try {
                Long seq = sequenceManager.getSeq(FaSysConst.SHOP_CODE_SEQ_NAME);
                return FaSysConst.SHOP_CODE_PREFIX + String.format("%08d", seq);
            } catch (Exception e) {
                LOGGER.warn("获取 shop code 异常,重试次数[{}]", i, e);
                try {
                    Thread.sleep(RandomUtils.nextInt(0, RANDOM_SHEEP_TIME));
                } catch (InterruptedException ie) {
                    LOGGER.warn(ie.getMessage(), ie);
                }
            }
        }
        LOGGER.error("获取 shop code失败");
        return null;
    }

    public String generateMerchantManagerCode() {

        for (int i = 0; i < GENERATE_RETRY_TIMES; i++) {
            try {
                Long seq = sequenceManager.getSeq(FaSysConst.MERCHANT_CODE_SEQ_NAME);
                return FaSysConst.MERCHANT_CODE_PREFIX + String.format("%08d", seq);
            } catch (Exception e) {
                LOGGER.warn("获取 merchant code 异常,重试次数[{}]", i, e);
                try {
                    Thread.sleep(RandomUtils.nextInt(0, RANDOM_SHEEP_TIME));
                } catch (InterruptedException ie) {
                    LOGGER.warn(ie.getMessage(), ie);
                }
            }
        }
        LOGGER.error("获取 merchant code失败");
        return null;
    }
}
