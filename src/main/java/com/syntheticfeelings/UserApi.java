package com.syntheticfeelings;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syntheticfeelings.dao.UserDao;
import com.syntheticfeelings.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/user")
public class UserApi {

    private Gson gson = new GsonBuilder().create();

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addNewUser(@FormParam("email") String email,
                           @FormParam("password") String password,
                           @FormParam("telephone") int telephone) {
        User user = new User(email, password, telephone);

        UserDao.getInstance().addUser(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return gson.toJson(UserDao.getInstance().getUsers());
    }

    @POST
    @Path("/authorize")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String authorizeUser(@FormParam("email") String email,
                                @FormParam("password") String password) {
        return UserDao.getInstance().authorizeUser(email, password);
    }
}

