package com.socialbook.users.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.socialbook.users.entities.User;
import com.socialbook.users.services.UserDto;
import com.socialbook.users.services.UsersBean;
import com.socialbook.users.services.interceptors.CollectRequests;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin
public class UsersResource {

    @Inject
    UsersBean usersBean;

    @GET
    @CollectRequests
    public Response getAllUsers() {
        List<UserDto> users = usersBean.getUsers();
        if (users != null) {
            return Response.ok(users).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{userId}")
    @CollectRequests
    public Response getUser(@PathParam("userId") Integer userId) {
        UserDto userDto = usersBean.getUser(userId);
        if (userDto != null) {
            return Response.ok(userDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/login")
    @CollectRequests
    public Response loginUser(UserDto userDto) {
        UserDto loggedInUser = usersBean.validateLogin(userDto);
        if (loggedInUser != null) {
            return Response.ok(loggedInUser)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Headers",
                            "origin, content-type, accept, authorization, body")
                    .header("Access-Control-Allow-Methods",
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .build();
        } else return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/login")
    @CollectRequests
    public Response loginUserGet(@QueryParam("username") String username, @QueryParam("password") String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        return loginUser(userDto);

    }

    @POST
    @Path("/register")
    @CollectRequests
    public Response registerUser(UserDto userDto) {
        UserDto registeredUser = usersBean.registerUser(userDto);
        if (registeredUser != null) {
            return Response.ok(registeredUser)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Headers",
                            "origin, content-type, accept, authorization, body")
                    .header("Access-Control-Allow-Methods",
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .entity("")
                    .build();
        } else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/register")
    @CollectRequests
    public Response registerUserGet(@QueryParam("username") String username,
                                    @QueryParam("password") String password,
                                    @QueryParam("name") String name,
                                    @QueryParam("surname") String surname,
                                    @QueryParam("imgref") String imgref) {

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setImgref(imgref);
        return registerUser(userDto);
    }

    @PUT
    @Path("/add/{userId}/{friendId}")
    @CollectRequests
    public Response addFriend(@PathParam("userId") Integer userId,
                              @PathParam("friendId") Integer friendId) {
        Boolean success = usersBean.addFriend(userId, friendId);
        if (success) {
            return Response.status(Response.Status.NO_CONTENT).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Headers",
                            "origin, content-type, accept, authorization, body")
                    .header("Access-Control-Allow-Methods",
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .entity("")
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/add/{userId}/{friendId}")
    @CollectRequests
    public Response addFriendGet(@PathParam("userId") Integer userId,
                                 @PathParam("friendId") Integer friendId) {
        return addFriend(userId, friendId);
    }

    @DELETE
    @Path("/remove/{userId}/{friendId}")
    @CollectRequests
    public Response removeFriend(@PathParam("userId") Integer userId,
                                 @PathParam("friendId") Integer friendId) {
        Boolean success = usersBean.removeFriend(userId, friendId);
        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
