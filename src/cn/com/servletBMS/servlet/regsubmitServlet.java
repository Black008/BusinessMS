package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.User;
import cn.com.servletBMS.service.userService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class regsubmitServlet extends HttpServlet {
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
        int num = userservice.Insertuser(user);
        if(num>0){
            resp.getWriter().write("注册成功请登录");
        }
    }
}
