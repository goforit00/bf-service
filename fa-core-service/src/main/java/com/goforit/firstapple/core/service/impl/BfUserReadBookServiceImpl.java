package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.dao.BfBookInfoDao;
import com.goforit.firstapple.common.dao.BfReadBookProgressRecordDao;
import com.goforit.firstapple.common.dao.BfUserReadBookEventDao;
import com.goforit.firstapple.common.dao.BfWeiXinUserInfoDao;
import com.goforit.firstapple.common.model.BfBookInfo;
import com.goforit.firstapple.common.model.BfReadBookProgressRecord;
import com.goforit.firstapple.common.model.BfUserReadBookEvent;
import com.goforit.firstapple.common.model.BfWeiXinUserInfo;
import com.goforit.firstapple.common.utils.DateTimeUtils;
import com.goforit.firstapple.core.service.BfUserReadBookService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @see com.goforit.firstapple.core.service.BfUserReadBookService
 *      Created by goforit on 18/2/11.
 */

@Service
public class BfUserReadBookServiceImpl implements BfUserReadBookService {

    @Autowired
    private BfUserReadBookEventDao bfUserReadBookEventDao;

    @Autowired
    private BfBookInfoDao bfBookInfoDao;

    @Autowired
    private BfWeiXinUserInfoDao bfWeiXinUserInfoDao;

    @Autowired
    private BfReadBookProgressRecordDao bfReadBookProgressRecordDao;

    //~~~~~~ public method ~~~~~~
    @Override
    public List<BfUserReadBookEvent> findUserReadEventByUserId(Long userId) {

        List<BfUserReadBookEvent> readBookEvents = bfUserReadBookEventDao.getReadEventByUserId(userId);
        fillBfUserReadBookEvents(readBookEvents);
        return readBookEvents;
    }

    @Override
    public List<BfUserReadBookEvent> findUserAroundReadEvent(Long userId) {

        List<BfUserReadBookEvent> readBookEvents = bfUserReadBookEventDao.getUserAroundReadEvent(userId);
        fillBfUserReadBookEvents(readBookEvents);
        return readBookEvents;
    }

    @Override
    public void publishBookEvent(BfUserReadBookEvent bfUserReadBookEvent) {

        if (null == bfUserReadBookEvent.getBookId()) {
            // 不是现有的图书,创建图书信息
            String bookName = bfUserReadBookEvent.getBookName();
            String author = bfUserReadBookEvent.getBookAuthor();

            // TODO 二期修正获取图书信息的方式
            List<BfBookInfo> existBooks = bfBookInfoDao.getByBookNameAndAuthor(bookName);
            if (CollectionUtils.isEmpty(existBooks)) {

                BfBookInfo bookInfo = bfUserReadBookEvent.buildBookInfo();

                bfBookInfoDao.insert(bookInfo);
                bfUserReadBookEvent.setBookId(bookInfo.getId());

            } else {
                BfBookInfo existBook;
                Optional<BfBookInfo> opt =
                    existBooks.stream().filter((b) -> StringUtils.equals(b.getAuthor(), author)).findFirst();
                if (opt.isPresent()) {
                    existBook = opt.get();
                } else {
                    existBook = existBooks.get(0);
                }
                bfUserReadBookEvent.setBookId(existBook.getId());

            }
        }

        bfUserReadBookEventDao.insert(bfUserReadBookEvent);

    }

    @Override
    public List<BfReadBookProgressRecord> findBookProgressByReadEventId(Long eventId) {

        return bfReadBookProgressRecordDao.findByReadEventId(eventId);

    }

    @Override
    public boolean existReadBookEvent(Long userId, Long eventId) {

        List<BfUserReadBookEvent> events=bfUserReadBookEventDao.getReadEventByUserId(userId);

        return events.stream().anyMatch((e)->e.getUserId().equals(userId));

    }

    @Override
    public BfReadBookProgressRecord publishBookProgress(BfReadBookProgressRecord progressRecord) {

        //TODO 检查读书进度百分比是否递增
        bfReadBookProgressRecordDao.insert(progressRecord);

        return bfReadBookProgressRecordDao.findById(progressRecord.getId());

    }

    //~~~~~~ private method ~~~~~~
    private void fillBfUserReadBookEvents(List<BfUserReadBookEvent> bfUserReadBookEvents) {

        for (BfUserReadBookEvent bookEvent : bfUserReadBookEvents) {

            BfBookInfo bookInfo = bfBookInfoDao.getByBookId(bookEvent.getBookId());

            if(null!=bookInfo){
                bookEvent.setBookName(bookInfo.getBookName());
                bookEvent.setBookCoverUrl(bookInfo.getPictureUrl());
                bookEvent.setBookAuthor(bookInfo.getAuthor());
            }

            BfWeiXinUserInfo userInfo = bfWeiXinUserInfoDao.getUserById(bookEvent.getUserId());

            bookEvent.setPenName(userInfo == null ? null : userInfo.getPenName());
            if(null!=bookEvent.getReadBeginTime()){
                bookEvent.setReadBeginTimeStr(DateTimeUtils.toDateString(bookEvent.getReadBeginTime()));
            }
        }

    }
}
