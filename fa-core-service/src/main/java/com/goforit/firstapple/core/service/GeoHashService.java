package com.goforit.firstapple.core.service;

/**
 * Created by goforit on 17/5/22.
 */
public interface GeoHashService {

    /**
     * 根据 经度 纬度 生成geohash
     * @param posLongitude 经度
     * @param posLatitude 纬度
     * @return
     */
    String generateGeoHash(Double posLongitude,Double posLatitude);
}
