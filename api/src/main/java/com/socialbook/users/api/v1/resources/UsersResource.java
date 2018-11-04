package com.socialbook.users.api.v1.resources;

import com.socialbook.users.entities.User;
import com.socialbook.users.services.UserDto;
import com.socialbook.users.services.UsersBean;

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
public class UsersResource {

    @Inject
    UsersBean usersBean;

    //GET get all users
    //GET get users friends
    //POST register user
    //POST login user
    //POST add friend

    @GET
    public Response getAllUsers() {
        List<UserDto> users = usersBean.getUsers();
        if (users != null) {
            return Response.ok(users).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUsersFriends(@PathParam("userId") Integer userId) {
        List<UserDto> friends = usersBean.getFriends(userId);
        if (friends != null) {
            return Response.ok(friends).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/login")
    public Response loginUser(UserDto userDto) {
        UserDto loggedInUser = usersBean.validateLogin(userDto);
        if (loggedInUser != null) {
            return Response.ok(loggedInUser).build();
        } else return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/register")
    public Response registerUser(UserDto userDto) {
        UserDto registeredUser = usersBean.registerUser(userDto);
        if (registeredUser != null) {
            return Response.ok(registeredUser).build();
        } else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/addFriend/{userId}/{friendId}")
    public Response addFriend(@PathParam("userId") Integer userId,
                              @PathParam("friendId") Integer friendId) {
        Boolean success = usersBean.addFriend(userId, friendId);
        if (success) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    //DELETE FRIEND
}
