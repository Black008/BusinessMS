package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.modle.Pages;
import cn.com.servletBMS.service.BusinessService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OtherButServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String totalNow = req.getParameter("totalNow");
        String totalPage = req.getParameter("totalPage");
        String ShowPages = req.getParameter("ShowPages");

        Pages pages = new Pages();
        pages.setNowPage(Integer.parseInt(totalNow));
        pages.setTotalPage(Integer.parseInt(totalPage));
        pages.setShowPages(Integer.parseInt(ShowPages));

        Business business = (Business) req.getSession().getAttribute("business");

        BusinessService businessService = new BusinessService();
        List<Business> result = businessService.ClickSelectByBusiness(business,pages);

        Gson gson = new Gson();
        String json = gson.toJson(result);
        resp.getWriter().write(json);

    }
}
