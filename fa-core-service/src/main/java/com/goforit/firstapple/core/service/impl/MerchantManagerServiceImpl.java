package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.consts.FaSysConst;
import com.goforit.firstapple.common.dao.MerchantManagerInfoDao;
import com.goforit.firstapple.common.dao.MerchantManagerTokenDao;
import com.goforit.firstapple.common.enums.MerchantManagerStatus;
import com.goforit.firstapple.common.enums.MerchantManagerType;
import com.goforit.firstapple.common.model.MerchantManagerInfo;
import com.goforit.firstapple.common.model.MerchantManagerToken;
import com.goforit.firstapple.common.model.params.MerchantManagerRegisterParams;
import com.goforit.firstapple.core.service.MerchantManagerService;
import com.goforit.firstapple.core.service.SequenceService;
import com.goforit.firstapple.core.service.UuidService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by goforit on 17/4/19.
 */
@Service
public class MerchantManagerServiceImpl implements MerchantManagerService {

    private static final Logger LOGGER= LoggerFactory.getLogger(MerchantManagerServiceImpl.class);

    @Autowired
    private MerchantManagerTokenDao merchantManagerTokenDao;

    @Autowired
    private MerchantManagerInfoDao merchantManagerInfoDao;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private UuidService uuidService;

    public MerchantManagerInfo register(MerchantManagerRegisterParams registerParams) {

        String email=registerParams.getEmail();
        MerchantManagerInfo merchantManagerInfo = merchantManagerInfoDao.getByEmail(email);
        if(null!= merchantManagerInfo){
            throw new RuntimeException(email+"已经被注册使用过,请使用其他邮箱");
        }

        MerchantManagerInfo managerInfo=initMerchantManagerInfo(registerParams);

        merchantManagerInfoDao.create(managerInfo);

        return merchantManagerInfoDao.getByEmail(email);
    }

    public MerchantManagerToken login(String email, String password) {

        MerchantManagerInfo merchantManagerInfo = merchantManagerInfoDao.getByEmail(email);
        //TODO 生产manager Code 和其他属性

        if(null== merchantManagerInfo){
            throw new RuntimeException("此用户未注册");
        }

        if(!StringUtils.equals(merchantManagerInfo.getPassword(),password)){
            throw new RuntimeException("用户密码不正确");
        }

        return generatorManagerCodeToken(merchantManagerInfo.getManagerCode());
    }


    public MerchantManagerInfo findByEmail(String email) {

        return merchantManagerInfoDao.getByEmail(email);

    }

    public MerchantManagerInfo findByManagerCode(String managerCode) {

        return merchantManagerInfoDao.getByManagerCode(managerCode);
    }

    public boolean checkManagerToken(String managerCode, String token) {

        MerchantManagerToken merchantManagerToken = merchantManagerTokenDao.findByManagerCode(managerCode);

        if (null == merchantManagerToken) {
            return false;
        }

        if (!StringUtils.equals(token, merchantManagerToken.getToken())) {
            return false;
        }

        //检查是否超时
        Date tokenTimeout= DateUtils.addMinutes(merchantManagerToken.getGmtModified(), FaSysConst.LOGIN_TOKEN_TIME_OUT_MINUTES);

        if(tokenTimeout.before(new Date())){
            //超时
            throw new RuntimeException("登录超时");
        }

        return true;
    }

    /**
     * 生成
     * @param managerCode
     * @return
     */
    private MerchantManagerToken generatorManagerCodeToken(String managerCode){

        //创建token
        String tokenStr=uuidService.buildUUID();

        MerchantManagerToken oldToken= merchantManagerTokenDao.findByManagerCode(managerCode);
        if(null==oldToken){

            MerchantManagerToken token=new MerchantManagerToken(managerCode,tokenStr);
            merchantManagerTokenDao.created(token);

        }else {

            oldToken.setToken(tokenStr);
            merchantManagerTokenDao.update(oldToken);
        }

        return merchantManagerTokenDao.findByManagerCode(managerCode);
    }

    /**
     * 初始化商家信息
     * @param registerParams
     * @return
     */
    private MerchantManagerInfo initMerchantManagerInfo(MerchantManagerRegisterParams registerParams){
        MerchantManagerInfo managerInfo=new MerchantManagerInfo(registerParams);
        String managerCode=sequenceService.generateMerchantManagerCode();
        if(StringUtils.isBlank(managerCode)){
            LOGGER.error("创建merchant manager code失败. email[{}]",registerParams.getEmail());
            throw new RuntimeException("商家信息创建失败,请重试");
        }
        managerInfo.setManagerCode(managerCode);
        managerInfo.setType(MerchantManagerType.NORMAL);
        managerInfo.setStatus(MerchantManagerStatus.UN_CHECK);
        return managerInfo;
    }
}
