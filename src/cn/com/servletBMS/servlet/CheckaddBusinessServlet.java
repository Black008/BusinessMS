package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.service.BusinessService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckaddBusinessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String name = req.getParameter("name");
        Business business = new Business();
        business.setName(name);
        BusinessService businessService = new BusinessService();
        int num = businessService.CheckBusinessByname(business);
        if(num>0){
            resp.getWriter().write(String.valueOf(num));
        }
    }
}
