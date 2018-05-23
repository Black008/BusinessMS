package cn.com.servletBMS.service;

import cn.com.servletBMS.dao.userDao;
import cn.com.servletBMS.modle.User;

public class userService {
    /*登陆时注册时查找用户*/
    public int Selectuser(User user){
        userDao userdao = new userDao();
        int num = userdao.Selectuser(user);
        return num;
    }
    /*注册时插入数据*/
    public int Insertuser(User user){
        userDao userdao = new userDao();
        int num = userdao.Insertuser(user);
        return num;
    }
/*    *//*登陆时检查用户名是否跟密码一致*//*
    public int SelectuserPdw(User user){
        userDao userdao = new userDao();
        int num = userdao.SelectuserPdw(user);
        return num;
    }*/

    /*登陆时检查用户名是否跟密码一致*/
    public int SelectuserPdw(User user){
        userDao userdao = new userDao();
        int num = userdao.SelectuserPdw(user);
        return num;
    }

    }

