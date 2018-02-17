package com.goforit.firstapple.common.consts;

/**
 * 第一个苹果系统常量
 *
 * Created by goforit on 17/4/5.
 */
public abstract class FaSysConst {

    private FaSysConst(){}

    public static final String PAGE_DOMAIN_KEY="page.domain";

    public static final String PAGE_PATH_KEY="page.path";


    public static final String COOKIE_USERNAME_KEY ="fa_username";

    public static final String COOKIE_TOKEN_KEY ="fa_token";

    public static final String PAGE_COOKIE_MANAGER_CODE_KEY="fa_manager_code";

    public static final String PAGE_COOKIE_MANAGER_TOKEN_KEY="fa_manager_token";

    public static final String PAGE_COOKIE_USER_PAGE_LOGIN_TYPE="fa_login_user_type";

    /**
     * 登录token超时分钟数
     */
    public static final Integer LOGIN_TOKEN_TIME_OUT_MINUTES = 30;

    /**
     * db中长度单位 丝米 转换为厘米
     */
    public static final Double DB_UNIT_TO_CM = 100.0;

    /**
     * db中价格单位 分 转换为 元
     */
    public static final Double DB_UNIT_TO_YUAN = 100.0;

    public static final String COMPANY_CODE_SEQ_NAME="COMPANY_SEQ_NAME";

    public static final String SHOP_CODE_SEQ_NAME="SHOP_SEQ_NAME";

    public static final String MERCHANT_CODE_SEQ_NAME="MERCHANT_SEQ_NAME";

    /**
     * company code 前缀
     * First Apple Company Code
     */
    public static final String COMPANY_CODE_PREFIX="FACC";

    /**
     * shop code前缀
     * First Apple Shop Code
     */
    public static final String SHOP_CODE_PREFIX="FASC";


    /**
     * 商家编号前传
     * First Apple Merchant Code
     */
    public static final String MERCHANT_CODE_PREFIX="FAMC";


}
