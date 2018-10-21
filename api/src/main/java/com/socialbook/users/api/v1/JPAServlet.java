package com.socialbook.users.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialbook.users.entities.User;
import com.socialbook.users.services.UsersBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UsersBean usersBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<List> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<User> friends = usersBean.getFriends(4);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = objectMapper.writeValueAsString(friends);
        out.print(json);
        out.flush();

    }
}
