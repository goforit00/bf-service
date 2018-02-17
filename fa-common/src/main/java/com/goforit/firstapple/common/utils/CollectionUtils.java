package com.goforit.firstapple.common.utils;

import com.goforit.firstapple.common.utils.model.KeyExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goforit on 17/5/3.
 */
public class CollectionUtils {

    /**
     * 把List中类型为F的数据转为 类型为T的List
     *
     * @param fromList
     * @param keyExtractor
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F,T> List<T> convertList(List<F> fromList, KeyExtractor<F,T> keyExtractor){

        List<T> targetList=new ArrayList<T>();

        for(F f:fromList){
            T t=keyExtractor.extractor(f);
            targetList.add(t);
        }

        return targetList;
    }
}
