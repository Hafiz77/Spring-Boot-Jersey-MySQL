package com.basePackage.controller;

import com.basePackage.entity.User;
import com.basePackage.services.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Hafiz on 4/20/2016.
 */

@Controller
@Path("/users")
public class UserController {
    UserService userService=new UserService();
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(User user) {
        String UserJson = gson.toJson(user);
        logger.debug(">> create({})", UserJson);
        //LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<>();
        logger.info("Starting to create a person");

        try {
            Set<ConstraintViolation<User>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                int createPerson = userService.createUser(user);


                if(createPerson==0)
                {
                    serviceResponse.put("created", "unable to create user");
                }else{
                    logger.info("Successfully created User.");

                    serviceResponse.put("created", user);
                }
                return Response.status(Response.Status.OK).entity(serviceResponse).build();
            } else {
                logger.info("Failed to create a user due to field validation errors.");
                logger.debug("Unable to create a user due to validation errors using {}", user);
                serviceResponse.put("error", validateErrors.toString());

                return Response.status(400).entity(serviceResponse).build();
            }
        } catch (Exception e) {

        }
        logger.debug("<< create()");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public Response getUsers() {

        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        try {
            List<User> listPersons=userService.listUsers();
            if(listPersons==null)
            {
                response.put("users", Collections.emptyMap());
            }else{
                response.put("total", listPersons.size());
                response.put("persons", listPersons);
            }

            return Response.status(Response.Status.OK).entity(response).build();
        }
        catch (Exception ex) {

        }
        response.put("user", "Not Found");

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(User user) {
        String personJson = gson.toJson(user);
        logger.debug(">> create({})", personJson);
        //LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<>();
        logger.info("Starting to create a person");

        try {
            Set<ConstraintViolation<User>> validateErrors = validator.validate(user);
            if (validateErrors.isEmpty()) {
                int updateUser = userService.updateUser(user);


                if(updateUser==0)
                {
                    serviceResponse.put("created", "unable to update User");
                }else{
                    logger.info("Successfully update user.");

                    serviceResponse.put("update", user);
                }
                return Response.status(Response.Status.OK).entity(serviceResponse).build();
            } else {
                logger.info("Failed to update a user due to field validation errors.");
                logger.debug("Unable to update a user due to validation errors using {}", personJson);
                serviceResponse.put("error", validateErrors.toString());

                return Response.status(400).entity(serviceResponse).build();
            }
        } catch (Exception e) {

        }
        logger.debug("<< create()");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPerson(@PathParam("id") int userId) {

        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

        try {
            User user=userService.getUser(userId);

            if(user==null)
            {
                response.put("User", Collections.emptyMap());
            }else{
                response.put("person", user);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        }
        catch (Exception ex) {

        }
        response.put("user", "Not Found");

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(@PathParam("id") int userId) {

        //LinkedHashMap<Object, Object> apiResponse = new LinkedHashMap<>();
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<>();
        logger.info("Starting to delete a person");

        try {
            int deletePerson = userService.deleteUser(userId);


            if(deletePerson==0)
            {
                serviceResponse.put("delete", "unable delete person");
            }else{
                logger.info("Successfully delete person.");

                serviceResponse.put("delete", "Successfully delete person.");
            }
            return Response.status(Response.Status.OK).entity(serviceResponse).build();

        } catch (Exception e) {

        }
        logger.debug("<< create()");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serviceResponse).build();
    }


}
