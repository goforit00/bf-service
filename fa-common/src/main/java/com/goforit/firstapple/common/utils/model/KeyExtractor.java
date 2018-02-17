package com.goforit.firstapple.common.utils.model;

/**
 * Created by goforit on 17/5/3.
 */
public interface KeyExtractor<T,K> {

    K extractor(T target);
}
