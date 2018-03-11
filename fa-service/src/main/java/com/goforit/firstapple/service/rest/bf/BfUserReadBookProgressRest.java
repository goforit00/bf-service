package com.goforit.firstapple.service.rest.bf;

import com.goforit.firstapple.common.model.BfReadBookProgressRecord;
import com.goforit.firstapple.common.model.BfUserReadBookEvent;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.BfUserReadBookService;
import com.goforit.firstapple.service.model.params.BfUserReadBookProgressParams;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by goforit on 18/2/24.
 */
@Path("/bf/book/progress")
public class BfUserReadBookProgressRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BfUserReadBookProgressRest.class);

    @Autowired
    private BfUserReadBookService bfUserReadBookService;

    @Path("/findByEventId/{eventId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<List<BfUserReadBookEvent>> queryUserAroundReadBookEvent(@PathParam("eventId") Long eventId) {

        List<BfReadBookProgressRecord> progressRecords = bfUserReadBookService.findBookProgressByReadEventId(eventId);
        if (CollectionUtils.isEmpty(progressRecords)) {
            return Response.buildFailed("获取失败");
        }
        return Response.buildSuccess(progressRecords);
    }

    @Path("/publish")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BfReadBookProgressRecord> publishReadBookProgress(
        BfUserReadBookProgressParams readBookProgressParams) {

        Long userId = readBookProgressParams.getUserId();
        Long eventId = readBookProgressParams.getReadBookEventId();

        if (!bfUserReadBookService.existReadBookEvent(userId, eventId)) {
            LOGGER.error("userId[{}]不存在id为[{}]的读书事件", userId, eventId);
            return Response.buildFailed("所发布的读书事件有问题");
        }

        BfReadBookProgressRecord progressRecord = readBookProgressParams.buildReadBookProgressRecord();

        progressRecord = bfUserReadBookService.publishBookProgress(progressRecord);

        return Response.buildSuccess(progressRecord);
    }
}
