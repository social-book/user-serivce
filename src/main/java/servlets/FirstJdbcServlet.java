package servlets;

import com.social.prpo.samplejdbc.BaseDao;
import com.social.prpo.samplejdbc.UserDaoImpl;
import com.social.prpo.samplejdbc.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet")
public class FirstJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BaseDao userDao = UserDaoImpl.getInstance();
//        UserEntity entity = (UserEntity) userDao.getEntity(1);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("{\"test\":123}");
//        out.print(entity.getUsername());
        out.flush();
    }
}
