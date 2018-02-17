package com.goforit.firstapple.common.utils;

import com.goforit.firstapple.common.consts.FaSysConst;

import java.math.BigDecimal;

import static com.goforit.firstapple.common.consts.FaSysConst.DB_UNIT_TO_YUAN;

/**
 * Created by goforit on 17/4/5.
 */
public abstract class NumberUtils {


    public static final double TWO_DIGITS_REMAIN = 100.0;


    /**
     * 保留2位小数
     * @param number
     * @return
     */
    public static double remainTwoDecimal(double number) {
        return (double) Math.round(number * TWO_DIGITS_REMAIN) / TWO_DIGITS_REMAIN;
    }

    /**
     * 保留n位小数
     * @param number
     * @param n
     * @return
     */
    public static double remainNDecimal(double number,int n){
        return (double) Math.round(number * Math.pow(10,n)) / Math.pow(10,n);
    }


    /**
     * DB长度单位(丝米)转换为cm
     * @param len cm*100
     * @return cm
     */
    public static  Double toCM(Integer len) {
        if (null == len) {
            return BigDecimal.ZERO.doubleValue();
        }
        return (len / FaSysConst.DB_UNIT_TO_CM);
    }

    /**
     * DB价格转换为元
     * @param price 元*100
     * @return
     */
    public static  Double toYUAN(Integer price) {
        if (null == price) {
            return BigDecimal.ZERO.doubleValue();
        }
        return (price / DB_UNIT_TO_YUAN);
    }

}
