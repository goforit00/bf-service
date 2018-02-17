package com.goforit.firstapple.service.rpc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 17/5/25.
 */
@Path("/heathCheck")
public class HelloWorldRpcImpl {

    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String check(@QueryParam("name") String name){
        return "Hello "+name;
    }
}
