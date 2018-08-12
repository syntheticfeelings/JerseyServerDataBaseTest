package com.syntheticfeelings;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syntheticfeelings.dao.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;


@Path("/user")
public class UserApi {

    private Gson gson = new GsonBuilder().create();

//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public void addNewUser(@FormParam("first_name") String firstName,
//                           @FormParam("second_name") String secondName,
//                           @FormParam("age") int age) {
//        User user = new User(firstName, secondName, age);
//
//        UserDao.getInstance().addUser(user);
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return gson.toJson(UserDao.getInstance().getUsers());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String authorizeUser(@FormParam("first_name") String firstName,
                                @FormParam("second_name") String secondName) {
        if (UserDao.getInstance().authorizeUser(firstName, secondName) == true) {
            return UUID.randomUUID().toString();
        }
        return "User is not found";
    }
}

