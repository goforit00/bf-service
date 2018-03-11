package com.goforit.firstapple.service.rpc;

import org.glassfish.jersey.server.ResourceConfig;

import com.goforit.firstapple.service.rest.bf.BfUserReadBookEventRest;
import com.goforit.firstapple.service.rest.bf.BfUserReadBookProgressRest;
import com.goforit.firstapple.service.rest.bf.BfWeiXinUserRest;

/**
 * Created by goforit on 17/5/25.
 */
public class RestJaxRsApplication extends ResourceConfig {

    public RestJaxRsApplication(){
        packages("com.goforit.firstapple.service.rpc");
        packages("com.goforit.firstapple.service.rest.bf");

        register(HelloWorldRpcImpl.class);
        register(BfWeiXinUserRest.class);
        register(BfUserReadBookEventRest.class);
        register(BfUserReadBookProgressRest.class);

    }
}
