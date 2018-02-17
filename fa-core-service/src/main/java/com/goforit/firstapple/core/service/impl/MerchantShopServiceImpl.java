package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.dao.ManagerShopMappingDao;
import com.goforit.firstapple.common.dao.MerchantShopInfoDao;
import com.goforit.firstapple.common.enums.MerchantShopStatus;
import com.goforit.firstapple.common.model.ManagerShopMappingInfo;
import com.goforit.firstapple.common.model.MerchantShopInfo;
import com.goforit.firstapple.common.utils.GeoHashUtils;
import com.goforit.firstapple.common.utils.model.KeyExtractor;
import com.goforit.firstapple.core.service.MerchantShopService;
import com.goforit.firstapple.core.service.SequenceService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Created by goforit on 17/5/3.
 */
@Service
public class MerchantShopServiceImpl implements MerchantShopService {

    @Autowired
    private MerchantShopInfoDao merchantShopInfoDao;

    @Autowired
    private ManagerShopMappingDao managerShopMappingDao;

    @Autowired
    private SequenceService sequenceService;


    /**
     * 创建店铺
     * @param merchantShopInfo
     * @return
     */
    public MerchantShopInfo createShop(MerchantShopInfo merchantShopInfo,String managerCode) {

        Assert.notNull(merchantShopInfo,"店铺实体信息不能为空");
        Assert.isTrue(StringUtils.isNotBlank(managerCode),"店铺管理者managerCode不能为空");
        //TODO 验证地址信息

        //生成shopCode
        String shopCode=sequenceService.generateShopCode();
        Assert.isTrue(StringUtils.isNotBlank(shopCode),"生成shopCode失败");
        merchantShopInfo.setStatus(MerchantShopStatus.INACTIVITY);
        merchantShopInfo.setShopCode(shopCode);

        //TODO 设置companyCode


        //生成geoHash
        String geoHash =
                GeoHashUtils.getPositionGeoHash(merchantShopInfo.getPosLongitude(), merchantShopInfo.getPosLatitude());
        merchantShopInfo.setGeoHash(geoHash);

        //创建关联关系
        managerShopMappingDao.create(new ManagerShopMappingInfo(managerCode,shopCode));

        //创建店铺信息
        merchantShopInfoDao.create(merchantShopInfo);

        return merchantShopInfoDao.findByShopCode(shopCode);
    }

    public MerchantShopInfo updateShop(MerchantShopInfo merchantShopInfo) {
        Assert.notNull(merchantShopInfo,"更新店铺实体信息不能为空");
        String shopCode=merchantShopInfo.getShopCode();
        Assert.isTrue(StringUtils.isNotBlank(shopCode),"店铺唯一标识不能为空");

        MerchantShopInfo oldShopInfo = merchantShopInfoDao.findByShopCode(shopCode);
        if(null==oldShopInfo){
            throw new RuntimeException("未找到店铺:"+shopCode);
        }

        oldShopInfo.updateByAnother(merchantShopInfo);

        merchantShopInfoDao.updateByShopCode(oldShopInfo);

        return merchantShopInfoDao.findByShopCode(shopCode);
    }

    /**
     * 通过managerCode查询店铺
     * @param managerCode
     * @return
     */
    public List<MerchantShopInfo> findByManagerCode(String managerCode) {

        List<ManagerShopMappingInfo> mappingInfos = managerShopMappingDao.findByManagerCode(managerCode);

        if (CollectionUtils.isEmpty(mappingInfos)) {
            return Collections.EMPTY_LIST;
        }

        List<String> shopCodes =
            com.goforit.firstapple.common.utils.CollectionUtils.convertList(mappingInfos,
                new KeyExtractor<ManagerShopMappingInfo, String>() {
                    public String extractor(ManagerShopMappingInfo target) {
                        return target.getShopCode();
                    }
                });

        List<MerchantShopInfo> shopInfos = merchantShopInfoDao.findShopsByShopCodes(shopCodes);

        return shopInfos;
    }

    public MerchantShopInfo findByShopCode(String shopCode) {

        return merchantShopInfoDao.findByShopCode(shopCode);
    }
}
