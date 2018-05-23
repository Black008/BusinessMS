package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.User;
import cn.com.servletBMS.service.userService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckloginPwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("user");
        String password = req.getParameter("pwd");
        User user = new User();
        user.setUser(username);
        user.setPaw(password);

        userService userservice = new userService();
       /* int num = userservice.SelectuserPdw(user);*/
        int num = userservice.SelectuserPdw(user);
       /* req.getSession().setAttribute("user2",user2);*/
       /* */
        Gson gson = new Gson();
        String json = gson.toJson(num);


       if(num>0){
           req.getSession().setAttribute("user2",username);
        }
        resp.getWriter().write(json);
       /*else{
            String  s ="密码不正确";
            resp.getWriter().write(s);
        }*/

    }
}
