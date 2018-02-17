package com.goforit.firstapple.dashboard.controller;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by goforit on 17/3/26.
 */
public class RestJaxRsApplication extends ResourceConfig {


    public RestJaxRsApplication(){
        packages("com.goforit.firstapple.dashboard.controller");

        register(HelloWorldController.class);
        register(UserController.class);
        register(MerchantManagerInfoController.class);
        register(MerchantShopInfoController.class);

    }


}
