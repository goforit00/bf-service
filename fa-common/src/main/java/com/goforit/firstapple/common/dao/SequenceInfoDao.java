package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.SequenceInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 17/4/19.
 */
public interface SequenceInfoDao {

    SequenceInfo selectBySeqName(String seqName);

    int updateBySeqNameAndCurValue(@Param("seqName") String seqName, @Param("curValue") Long curValue,
        @Param("targetValue") Long targetValue);

}
