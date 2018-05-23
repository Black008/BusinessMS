package cn.com.servletBMS.servlet;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.modle.ResultTotal;
import cn.com.servletBMS.service.BusinessService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectByBusinessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String businessId = req.getParameter("id");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String kinds = req.getParameter("kinds");
        String address = req.getParameter("address");
        String detailaddress = req.getParameter("detailaddress");
        String salaryfrom = req.getParameter("salaryFrom");
        String salaryto = req.getParameter("salaryTo");

        Business business = new Business();
        business.setBusinessId(businessId);
        business.setName(name);
        business.setPhone(phone);
        business.setKinds(kinds);
        business.setAddress(address);
        business.setDetailaddress(detailaddress);
        if(salaryfrom != null&& !"".equals(salaryfrom)){
            business.setSalaryFrom(Integer.parseInt(salaryfrom));
        }
        if(salaryto != null&& !"".equals(salaryto)) {
            business.setSalaryTo(Integer.parseInt(salaryto));
        }

        BusinessService businessService = new BusinessService();
        List<Business> result = businessService.SelectByBusiness(business);
        int total = businessService.ReturntotalByBusinessSelect(business);
        ResultTotal resultTotal = new ResultTotal();
        resultTotal.setResult(result);
        resultTotal.setTotal(total);
        /*json返回的时ResultTotal的对象（包含总条数，每页显示几条的集合）*/

        /*点击查询的时候把数据放到session里面*/
        req.getSession().setAttribute("business",business);

        Gson gson = new Gson();
        String json = gson.toJson(resultTotal);
        /*req.getSession().setAttribute("count",result.size());*/
        resp.getWriter().write(json);
      /*  req.getSession().setAttribute("result",result);
        req.getRequestDispatcher("/centerDetailsPage.jsp").forward(req,resp);*/
       /* req.getRequestDispatcher("/tableTest.jsp").forward(req,resp);*/




    }
}
