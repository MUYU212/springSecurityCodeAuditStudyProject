package com.red.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.red.pojo.User;
import com.red.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class LoginAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    public String login(){

        return "login";
    }
    public String logon(){

        User result = findUsersByName(user.getUsername());
        System.out.println(user.getUsername()+"====="+user.getPassword());
        if (result!=null){
            return "success";
        }
        return "error";
    }
    public User findUsersByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM user WHERE username = '" + name + "'";
        System.out.println(sql);
        Query query = session.createNativeQuery(sql, User.class);
        return (User)query.uniqueResult();
    }
    public User getModel() {
        return user;
    }
}
