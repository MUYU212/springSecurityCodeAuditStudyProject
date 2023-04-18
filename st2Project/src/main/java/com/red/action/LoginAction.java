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

    public String login() {

        return "login";
    }

    public String logon() {

        String result = findUsersByName(user.getUsername(), user.getPassword());
//        System.out.println(user.getUsername()+"====="+user.getPassword());

        return result;
    }

    public String findUsersByName(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        String sql = "select * from user where username='" + name + "'";
        String hql = "from User where username='" + name + "'" + " and password='" + password + "'";
        try {
            Query query = session.createQuery(hql);
            String Qstring = query.getQueryString();
            System.out.println(Qstring);
            List<User> users = query.getResultList();;
            if (users != null && !users.isEmpty())
                users.forEach(user -> System.out.println(user));
                return "success";
        } catch (Exception e) {
            System.out.println("=====something error=====");
            System.out.println(hql);
            return "error";
        }
//        List<User> resultList = query.getResultList();
    }

    public User getModel() {
        return user;
    }
}
