package com.goforit.firstapple.common.utils;

import ch.hsr.geohash.GeoHash;

/**
 * Created by goforit on 17/4/28.
 */
public abstract class GeoHashUtils {

    private static final int DEFAULT_GEOHASH_PRECISION = 12;

    /**
     * 生成 geo hash ,默认经度12
     * @param lon 经度
     * @param lat 纬度
     * @return
     */
    public static String getPositionGeoHash(double lon,double lat){
        return getPositionGeoHash(lon,lat,DEFAULT_GEOHASH_PRECISION);
    }

    /**
     * 生成 geo hash
     * @param lon 经度
     * @param lat 纬度
     * @param precision 精度
     * @return
     */
    public static String getPositionGeoHash(double lon,double lat, int precision){
        GeoHash geoHash=GeoHash.withCharacterPrecision(lat,lon,precision);

        return geoHash.toBase32();
    }

}
