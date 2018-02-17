package com.goforit.firstapple.common.utils;

import ch.hsr.geohash.GeoHash;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by goforit on 17/4/13.
 */
public abstract class StringUtils {

    private static String[] NUMBER_ARRAY = new String[] { "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9" };

    /**
     * 生成 len长度不重复的数字字符串
     *
     * @param len
     * @return
     */
    public static String generateRandomNoSameNumStr(int len) {
        if (len <= 0) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        } else if (len > NUMBER_ARRAY.length) {
            len = NUMBER_ARRAY.length;
        }

        List numList = Arrays.asList(NUMBER_ARRAY);

        Collections.shuffle(numList);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(numList.get(i));
        }

        return sb.toString();
    }


    public static void main(String argv[]){
        double lat = 30.549608; // 纬度坐标
        double lon = 114.376971; // 经度坐标
        GeoHash geoHash =GeoHash.withCharacterPrecision(lat,lon,9);
        System.err.println(geoHash.toBase32());
    }
}
