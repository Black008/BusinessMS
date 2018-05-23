package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.service.BusinessService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBusinessServlet extends HttpServlet {
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
        String phone = req.getParameter("phone");
        String kinds = req.getParameter("kinds");
        String address = req.getParameter("address");
        String detailaddress = req.getParameter("detailaddress");
        String salary = req.getParameter("salary");


        Business business = new Business();
        business.setName(name);
        business.setPhone(phone);
        business.setKinds(kinds);
        business.setAddress(address);
        business.setDetailaddress(detailaddress);
        business.setSalary(Integer.parseInt(salary));

     /*   if(salaryto != null&& Integer.parseInt(salaryto)>Integer.parseInt(salaryfrom)) {
            business.setSalaryTo(Integer.parseInt(salaryto));
        }*/
        BusinessService businessService = new BusinessService();
        int num = businessService.InsertBusiness(business);

        Gson gson = new Gson();
        String json =gson.toJson(num);
        if(num>0){
            resp.getWriter().write(json);
        }







    }
}
