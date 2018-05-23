package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.service.BusinessService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UppdateFlgByDeleteBusinessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String[] businessId = req.getParameterValues("businessid[]");



        List<Business> businesslist = new ArrayList<>();
            for(String bid:businessId){
                Business business = new Business();
                business.setBusinessId(bid);
                businesslist.add(business);
            }


        BusinessService businessService = new BusinessService();
        int num = businessService.UppdateFlgByDeleteBusiness(businesslist);
        Gson gson = new Gson();
        String json = gson.toJson(num);
        resp.getWriter().write(json);


    }
}
