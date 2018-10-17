package com.socialbook.users.api.v1;

import com.socialbook.users.entities.User;
import com.socialbook.users.services.UsersBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UsersBean usersBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        docker run -d  -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=user -p 5432:5432 postgres:10.5
        List<User> users = usersBean.getUsers();

//        User user = usersBean.getUser(1);
//        System.out.println(users.toString());
        // TODO print users

    }
}
