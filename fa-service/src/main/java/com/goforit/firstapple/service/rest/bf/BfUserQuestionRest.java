package com.goforit.firstapple.service.rest.bf;

import com.goforit.firstapple.core.service.BfUserService;
import com.goforit.firstapple.service.model.params.BfUserQuestionParams;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by goforit on 18/2/13.
 */

@Path("/bf/question")
public class BfUserQuestionRest {

    @Autowired
    private BfUserService bfUserService;

    @Path("/submit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void submitQuestion(BfUserQuestionParams questionParams){

        bfUserService.submitQuestion(questionParams.getUserId(),questionParams.getQuestion());

    }





}
