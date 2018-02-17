package com.goforit.firstapple.core.service;

import com.goforit.firstapple.common.model.BfUserReadBookEvent;

import java.util.List;

/**
 * 用户读书事件 Service
 *
 * Created by goforit on 18/2/11.
 */
public interface BfUserReadBookService {

    /**
     * 发现某个用的读书事件
     *
     * @param userId
     * @return
     */
    List<BfUserReadBookEvent> findUserReadEventByUserId(Long userId);

    /**
     * 发现某个用户周围的读书时间
     * 周围: 地理位置附近或者 读相关书
     *
     * @param userId
     * @return
     */
    List<BfUserReadBookEvent> findUserAroundReadEvent(Long userId);


    /**
     * 发布读书事件
     *
     * @param bfUserReadBookEvent
     */
    void  publishBookEvent(BfUserReadBookEvent bfUserReadBookEvent);
}
