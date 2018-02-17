package com.goforit.firstapple.common.dao;

import com.goforit.firstapple.common.model.BfBookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by goforit on 18/2/11.
 */
public interface BfBookInfoDao {

    //~~~~~~ modified method ~~~~~~
    int insert(BfBookInfo bfBookInfo);

    //~~~~~~ query method ~~~~~~

    BfBookInfo getByBookId(Long id);

    /**
     * 根据书名 获取 图书信息
     * @param bookName
     * @return
     */
    List<BfBookInfo> getByBookNameAndAuthor(@Param("bookName") String bookName);
}
