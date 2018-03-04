package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.BfReadBookProgressRecord;

import java.util.List;

/**
 * Created by goforit on 18/2/24.
 */
public interface BfReadBookProgressRecordDao {

    //~~~~~ Modified Method ~~~~~~
    int insert(BfReadBookProgressRecord bfReadBookProgressRecord);

    //~~~~~ Query Method ~~~~~
    List<BfReadBookProgressRecord> findByReadEventId(Long eventId);

    BfReadBookProgressRecord findById(Long progressId);
}
