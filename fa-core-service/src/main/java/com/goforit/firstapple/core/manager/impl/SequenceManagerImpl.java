package com.goforit.firstapple.core.manager.impl;

import com.goforit.firstapple.common.dao.SequenceInfoDao;
import com.goforit.firstapple.common.model.SequenceInfo;
import com.goforit.firstapple.core.manager.SequenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by goforit on 17/4/19.
 */
@Service
public class SequenceManagerImpl implements SequenceManager {

    @Autowired
    private SequenceInfoDao sequenceInfoDao;

    /**
     * Map<seqName,SequenceInterval>
     */
    private Map<String,SequenceInterval> sequenceIntervalMap=new ConcurrentHashMap<String, SequenceInterval>();

    public Long getSeq(String seqName) {

        SequenceInterval sequenceInterval=sequenceIntervalMap.get(seqName);
        if(null==sequenceInterval || sequenceInterval.current.get()+1>sequenceInterval.right){
            Assert.notNull(seqName,"seqName不能为空");
            SequenceInfo sequenceInfo= sequenceInfoDao.selectBySeqName(seqName);

            Long targetValue=sequenceInfo.getValue()+sequenceInfo.getStep();
            if(targetValue>=sequenceInfo.getMax()){
                targetValue=sequenceInfo.getMin();
            }

            int updateResult= sequenceInfoDao.updateBySeqNameAndCurValue(seqName,sequenceInfo.getValue(),targetValue);
            if( 1!=updateResult){
                throw new RuntimeException("更新sequence target value失败,seqName="+seqName);
            }

            if(null==sequenceInterval){
                sequenceInterval=new SequenceInterval();
                sequenceIntervalMap.put(seqName,sequenceInterval);
            }

            synchronized (this){
                sequenceInterval.current.set(targetValue);
                sequenceInterval.right=targetValue+sequenceInfo.getStep();
            }
        }

        return sequenceInterval.current.incrementAndGet();
    }


    private class SequenceInterval {
        private AtomicLong current = new AtomicLong();
        private Long right;
    }
}
