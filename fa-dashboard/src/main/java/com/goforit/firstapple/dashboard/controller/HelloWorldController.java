package com.goforit.firstapple.dashboard.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 17/3/26.
 */

@Path("/heathCheck")
public class HelloWorldController {

    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String check(@QueryParam("name") String name){
        return "Hello "+name;
    }
}
