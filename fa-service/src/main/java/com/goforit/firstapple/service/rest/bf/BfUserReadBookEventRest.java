package com.goforit.firstapple.service.rest.bf;

import com.goforit.firstapple.common.model.BfUserReadBookEvent;
import com.goforit.firstapple.common.model.Response;
import com.goforit.firstapple.core.service.BfUserReadBookService;
import com.goforit.firstapple.service.model.params.BfUserReadBookEventParams;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by goforit on 18/2/9.
 */
@Path("/bf/book/event")
public class BfUserReadBookEventRest {

    @Autowired
    private BfUserReadBookService bfUserReadBookService;

    @Path("/queryAroundUser/{userId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<List<BfUserReadBookEvent>> queryUserAroundReadBookEvent(@PathParam("userId") Long userId) {

            List<BfUserReadBookEvent> events=bfUserReadBookService.findUserAroundReadEvent(userId);
        if(CollectionUtils.isEmpty(events)){
            return Response.buildFailed("获取失败");
        }
        return Response.buildSuccess(events);

    }

    @Path("/query/{userId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<List<BfUserReadBookEvent>> queryUserReadBookEvent(@PathParam("userId") Long userId) {

        List<BfUserReadBookEvent> events=bfUserReadBookService.findUserReadEventByUserId(userId);
        if(CollectionUtils.isEmpty(events)){
            return Response.buildFailed("获取失败");
        }
        return Response.buildSuccess(events);

    }

    @Path("/publish")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response<Void> publish(BfUserReadBookEventParams bfUserReadBookEventParams){

        bfUserReadBookService.publishBookEvent(bfUserReadBookEventParams.buildBookEvent());

        return Response.buildSuccess(null);
    }

}
