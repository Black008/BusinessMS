package cn.com.servletBMS.service;

import cn.com.servletBMS.dao.BusinessDao;
import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.modle.Pages;
import util.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BusinessService {
    /*添加商家*/
    public int InsertBusiness(Business business){
        BusinessDao businessDao = new BusinessDao();
        int num = businessDao.InsertBusiness(business);
        return num;
    }
    /*通过名字检查是否已经添加过了*/
    public int CheckBusinessByname(Business business){
        BusinessDao businessDao = new BusinessDao();
        int num = businessDao.CheckBusinessByname(business);
        return num;
    }
    /*点击查询按钮查询商家信息*/
    public List<Business> SelectByBusiness(Business business){
        BusinessDao businessDao = new BusinessDao();
        List<Business> myList = businessDao.SelectByBusiness(business);
        return myList;
    }
    /*点及其他按钮查询*/
    public List<Business> ClickSelectByBusiness(Business business,Pages pages){
        BusinessDao businessDao = new BusinessDao();
        List<Business> myList = businessDao.ClickSelectByBusiness(business,pages);
        return myList;
    }
    /*查询商家信息返回总条数*/
    public int ReturntotalByBusinessSelect(Business business) {
        BusinessDao businessDao = new BusinessDao();
        int total = businessDao.ReturntotalByBusinessSelect(business);
        return total;
    }


    /*更新商家的标志位*/
    public int UppdateFlgByDeleteBusiness(List<Business> businesslist){
        BusinessDao businessDao = new BusinessDao();
        int num = businessDao.UppdateFlgByDeleteBusiness(businesslist);
        return num;
    }
    /*修改商家时根据商家Id更新数据库的数据包括更新时间*/
    public int UppdateBusininess(Business business){
        BusinessDao businessDao = new BusinessDao();
        int num= businessDao.UppdateBusininess(business);
        return num;
    }
}
