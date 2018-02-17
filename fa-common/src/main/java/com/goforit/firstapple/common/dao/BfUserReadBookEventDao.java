package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.BfUserReadBookEvent;

import java.util.List;

/**
 * Created by goforit on 18/2/11.
 */
public interface BfUserReadBookEventDao {

    //~~~~~~ modified method ~~~~~~
    int insert(BfUserReadBookEvent bfUserReadBookEvent);

    //~~~~~~ query method ~~~~~~
    List<BfUserReadBookEvent> getReadEventByUserId(Long userId);

    List<BfUserReadBookEvent> getUserAroundReadEvent(Long userId);

}
