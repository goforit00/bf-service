package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.FAConfigInfo;

/**
 * Created by goforit on 17/4/4.
 */
public interface FAConfigDao {

    FAConfigInfo getByKey(String key);
}
