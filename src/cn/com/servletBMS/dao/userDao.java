package cn.com.servletBMS.dao;

import cn.com.servletBMS.modle.User;
import util.DBconnection;

import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class userDao {
    /*登陆时注册时查找用户*/
    public int Selectuser(User user){
        ResultSet rs = null;
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select user from t_user where user = ?";
            PreparedStatement sta1 = con.prepareStatement(sql);
            String a = user.getUser();
            sta1.setString(1,a);
            rs = sta1.executeQuery();
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
    /*注册后点击注册按钮将数据插入到数据库*/
    public int Insertuser(User user){
        int num=0;
        userDao userDao = new userDao();
        /*int us = userDao.Selectuser(user);*/
        try {
            Connection con = DBconnection.getDb();
            /*if(us==0){*/
                String sql = "insert into t_user (user,pwd,credate,upddate,deleflg) values (?,?,?,?,?);";
                PreparedStatement sta = con.prepareStatement(sql);
                sta.setString(1,user.getUser());
                sta.setString(2,user.getPaw());
                sta.setString(3,UserGetCurrTime());
                sta.setString(4,UserGetCurrTime());
                sta.setInt(5,0);//标志位
                num = sta.executeUpdate();
                return num;

           /* }*/


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    /*登陆时检查用户名是否跟密码一致同时更新一下当前时间*/
    public int SelectuserPdw(User user){
        int num=0;
        User user1 = new User();
        try {
            Connection con = DBconnection.getDb();
            String sql = "Select count(*) count from t_user where user = '"+user.getUser()+"' "+" and pwd ='"+user.getPaw()+"' ";
          /*  PreparedStatement sta1 = con.prepareStatement(sql);
            String a = user.getUser();
            String b = user.getPaw();
            sta1.setString(1,a);
            sta1.setString(2,b);*/
            /*rs = sta1.executeQuery();*/
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if(rs.next()){
                num = rs.getInt("count");
             /*  user1.setUser(rs.getString("user"));*/
               /*user1.setPaw(rs.getString("pwd"));*/
               //调用更新一下当前时间
                String sql1 = "UPDATE t_user SET upddate='"+UserGetCurrTime()+"' where user = '"+user.getUser()+"'";
                Statement sta1 = con.createStatement();
                sta1.executeUpdate(sql1);
                /* Upddate();*/
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
   /*登陆后更新时间*/
    /*  public void Upddate(){
        *//* List<User> us = new ArrayList<>();*//*
      User user = new User();
        *//*SelectuserPdw(user);
        System.out.println(user.getUser());//null*//*
        try {
            Connection con = DBconnection.getDb();
            String sql = "UPDATE t_user SET upddate='"+UserGetCurrTime()+"' where user = '"+user.getUser()+"'";
          *//*  PreparedStatement sta1 = con.prepareStatement(sql);
            String a = user.getUser();
            String b = user.getPaw();
            sta1.setString(1,a);
            sta1.setString(2,b);*//*
            *//*rs = sta1.executeQuery();*//*
            Statement sta = con.createStatement();
            sta.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
    /*获取当前的创建用户时间插入数据库*/
    public String UserGetCurrTime() {
        /*java获取当前的时间*/
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String time =  df.format(new Date());// new Date()为获取当前系统时间
        return time;
    }
}
