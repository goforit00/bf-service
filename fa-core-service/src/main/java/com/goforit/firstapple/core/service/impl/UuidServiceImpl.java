package com.goforit.firstapple.core.service.impl;

import com.goforit.firstapple.common.dao.UuidDao;
import com.goforit.firstapple.common.model.UuidInfo;
import com.goforit.firstapple.core.service.UuidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by goforit on 17/4/5.
 */

@Service
public class UuidServiceImpl implements UuidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidServiceImpl.class);

    @Autowired
    private UuidDao uuidDao;

    public String buildUUID() {

        boolean buildSuc = false;
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();

            try {
                uuidDao.create(new UuidInfo(uuid));
                buildSuc = true;

            } catch (DuplicateKeyException e) {
                LOGGER.warn("创建uuid发生重复建异常.", e);
            }
        } while (!buildSuc);

        return uuid;
    }
}
