package cn.com.servletBMS.dao;

import cn.com.servletBMS.modle.Business;
import cn.com.servletBMS.modle.Pages;
import cn.com.servletBMS.modle.User;
import util.DBconnection;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessDao {
    /*添加商家信息实现主键按照字符串000001递增*/
  /* public String SelectBusiness() {
        String str="";
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select empid from t_business order by empid desc limit 1";
            Statement sta1 = con.createStatement();
            ResultSet rs = sta1.executeQuery(sql);
            if(rs.next()){
                String str1 = rs.getString(1);
                str1="000000";
                int empid = Integer.parseInt(str1);
                empid++;
                String str2="00000";
                str1= str2+String.valueOf(empid);
                return str1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;

    }*/
    /*添加商家信息*/
    public int InsertBusiness(Business business) {
        int num = 0;
        Connection con = null;
        PreparedStatement sta = null;
        try {
            con = DBconnection.getDb();
         /*   con.setAutoCommit(false);*/
            String sql = "insert into t_business (businessId,businessName,phone,businessKinds,businessaddress,detailaddress,salary,cred,uppda,flg) values (?,?,?,?,?,?,?,?,?,?);";
            sta = con.prepareStatement(sql);
            /*sta.setString(1, getempId());*/
            /*sta.setString(1,SelectBusiness());*/
            sta.setString(1, "GYX"+BusinessGetCurrTime());
            sta.setString(2, business.getName());
            sta.setString(3, business.getPhone());
            //if(business.getKindsArrry()!=null) {
            //  for (String kinds : business.getKindsArrry()) {
            sta.setString(4, business.getKinds());
            sta.setString(5, business.getAddress());
            sta.setString(6, business.getDetailaddress());
            sta.setInt(7, business.getSalary());
            sta.setString(8, UserGetCurrTime());
            sta.setString(9,UserGetCurrTime());
            sta.setInt(10,0);//标志位
            num = sta.executeUpdate();
            /*  System.out.println(getempId());*/
          /*  con.commit();*/

        } catch (SQLException e) {
          /*  try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }*/
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                sta.close();
                con.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        return num;
    }
     /*删除所选的时候更新商家的标志位*/
    public int UppdateFlgByDeleteBusiness(List<Business> businesslist){
        int num=0;
        try {
            Connection con = DBconnection.getDb();
            for(int i=0;i<businesslist.size();i++){
           /*     String sql = "Select businessId from t_business where businessId='"+businesslist.get(i).getBusinessId()+"'";
                Statement sta = con.createStatement();
                ResultSet rs = sta.executeQuery(sql);
                if(rs.next()){*/
                    String s = businesslist.get(i).getBusinessId();
                    String sql1 = "UPDATE t_business SET uppda='"+UserGetCurrTime()+"' " + ",flg=1" + " where businessId='"+s+"'";
                    Statement sta1 = con.createStatement();
                    num=sta1.executeUpdate(sql1);
                    num++;
                /*}*/
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    /*修改商家时根据商家Id更新数据库的数据包括更新时间*/
    public int UppdateBusininess(Business business){
        int num = 0;
        try {
            Connection con = DBconnection.getDb();
            String sql = "UPDATE t_business SET uppda='"+UserGetCurrTime()+"'"+
                    ", phone='"+business.getPhone()+"'" +
                    ", businessKinds='"+business.getKinds()+"'" +
                    ", businessaddress='"+business.getAddress()+"'" +
                    ", detailaddress='"+business.getDetailaddress()+"'" +
                    ", salary='"+business.getSalary()+"'" +
                    "where businessId = '"+business.getBusinessId()+ "'";
            Statement sta = con.createStatement();
            num = sta.executeUpdate(sql);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    /*检查添加商家信息是否正确*/
    public int CheckBusinessByname(Business business){
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select businessName from t_business where businessName = ?";
            PreparedStatement sta1 = con.prepareStatement(sql);
            String a = business.getName();
            sta1.setString(1,a);
            ResultSet rs = sta1.executeQuery();
            if(rs.next()){
                return 1;
            }else{
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /*获取当前的创建用户时间插入数据库*/
    public String BusinessGetCurrTime() {
        /*java获取当前的时间*/
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");//设置日期格式
        String time =  df.format(new Date());// new Date()为获取当前系统时间
        return time;
    }
    /*查询商家信息每页默认显示5条信息*/
    public List<Business> SelectByBusiness(Business business){
        List<Business> myList = new ArrayList<Business>();

        try {
            Connection con = DBconnection.getDb();
            String sql = "Select businessId,businessName,phone,businessKinds,businessaddress,detailaddress,salary from t_business where flg=0";
            if(null!=business&&!"".equals(business.getBusinessId())){
                sql =sql+" and businessId like '%"+business.getBusinessId()+"%' ";
            }
            if(null!=business&&!"".equals(business.getName())){
                sql =sql+" and businessName like '%"+business.getName()+"%' ";
            }
            if(null!=business&&!"".equals(business.getPhone())){
                sql =sql+" and phone = '"+business.getPhone()+"' ";
            }
           if(business.getKinds()!=""){
                sql =sql+" and businessKinds = '"+business.getKinds()+"' ";
            }
            if(business.getAddress()!=""){
                sql =sql+" and businessaddress = '"+business.getAddress()+"' ";
            }
            if(null!=business&&!"".equals(business.getDetailaddress())){
                sql =sql+" and detailaddress like '%"+business.getDetailaddress()+"%' ";
            }

            sql = sql + " and salary > " +business.getSalaryFrom();

            if(null!=business && business.getSalaryTo()>0){
                sql = sql + " and salary < " +business.getSalaryTo();
            }
            sql=sql+" LIMIT 0,5";

            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while(rs.next()){
                Business business1 = new Business();

                business1.setBusinessId(rs.getString("businessId"));
                business1.setName(rs.getString("businessName"));
                business1.setPhone(rs.getString("phone"));
                business1.setKinds(rs.getString("businessKinds"));
                business1.setAddress(rs.getString("businessaddress"));
                business1.setDetailaddress(rs.getString("detailaddress"));
                business1.setSalary(rs.getInt("salary"));
                myList.add(business1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myList;
    }
    /*点及其他按钮查询商家信息*/
    public List<Business> ClickSelectByBusiness(Business business,Pages pages){
        List<Business> myList = new ArrayList<Business>();
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select businessId,businessName,phone,businessKinds,businessaddress,detailaddress,salary from t_business where flg=0";
            if(null!=business&&!"".equals(business.getBusinessId())){
                sql =sql+" and businessId like '%"+business.getBusinessId()+"%' ";
            }
            if(null!=business&&!"".equals(business.getName())){
                sql =sql+" and businessName like '%"+business.getName()+"%' ";
            }
            if(null!=business&&!"".equals(business.getPhone())){
                sql =sql+" and phone = '"+business.getPhone()+"' ";
            }
            if(business.getKinds()!="")
            {
                sql =sql+" and businessKinds = '"+business.getKinds()+"' ";
            }
            if(business.getAddress()!=""){
                sql =sql+" and businessaddress = '"+business.getAddress()+"' ";
            }
            if(null!=business&&!"".equals(business.getDetailaddress())){
                sql =sql+" and detailaddress like '%"+business.getDetailaddress()+"%' ";
            }
            if(null!=business && business.getSalaryFrom()>0){
                sql = sql + " and salary > " +business.getSalaryFrom();
            }
            if(null!=business && business.getSalaryTo()>0){
                sql = sql + " and salary < " +business.getSalaryTo();
            }
            sql=sql+" LIMIT "+(pages.getNowPage()-1)*pages.getShowPages()+","+pages.getShowPages()+"";

            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while(rs.next()){
                Business business1 = new Business();

                business1.setBusinessId(rs.getString("businessId"));
                business1.setName(rs.getString("businessName"));
                business1.setPhone(rs.getString("phone"));
                business1.setKinds(rs.getString("businessKinds"));
                business1.setAddress(rs.getString("businessaddress"));
                business1.setDetailaddress(rs.getString("detailaddress"));
                business1.setSalary(rs.getInt("salary"));
                myList.add(business1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myList;
    }
    /*查询商家信息返回值总条数*/
    public int ReturntotalByBusinessSelect(Business business){
        int total=0;
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select count(*) count from t_business where flg=0";
            if(null!=business&&!"".equals(business.getBusinessId())){
                sql =sql+" and businessId like '%"+business.getBusinessId()+"%' ";
            }
            if(null!=business&&!"".equals(business.getName())){
                sql =sql+" and businessName like '%"+business.getName()+"%' ";
            }
            if(null!=business&&!"".equals(business.getPhone())){
                sql =sql+" and phone = '"+business.getPhone()+"' ";
            }
            if(business.getKinds()!=""){
                sql =sql+" and businessKinds = '"+business.getKinds()+"' ";
            }
            if(business.getAddress()!=""){
                sql =sql+" and businessaddress = '"+business.getAddress()+"' ";
            }
            if(null!=business&&!"".equals(business.getDetailaddress())){
                sql =sql+" and detailaddress like '%"+business.getDetailaddress()+"%' ";
            }
            if(null!=business && business.getSalaryFrom()>0){
                sql = sql + " and salary > " +business.getSalaryFrom();
            }
            if(null!=business && business.getSalaryTo()>0){
                sql = sql + " and salary < " +business.getSalaryTo();
            }
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()){
                total=rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
    /*获取当前的创建用户时间插入数据库*/
    public String UserGetCurrTime() {
        /*java获取当前的时间*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time =  df.format(new Date());// new Date()为获取当前系统时间
        return time;
    }



}
