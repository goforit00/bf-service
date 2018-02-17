package com.goforit.firstapple.common.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by goforit on 18/2/13.
 */
public interface BfUserQuestionDao {

    //~~~~~~ modified method ~~~~~~

    int insert(@Param("userId") Long userId,@Param("question") String question);

    //~~~~~~ query method ~~~~~~

}
