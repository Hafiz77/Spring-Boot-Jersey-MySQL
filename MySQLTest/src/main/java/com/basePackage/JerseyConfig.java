package com.basePackage;

import com.basePackage.controller.UserController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Hafiz on 4/20/2016.
 */

@Configuration
@Controller
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(MultiPartFeature.class);
        //Controller
        register(UserController.class);
    }
}
