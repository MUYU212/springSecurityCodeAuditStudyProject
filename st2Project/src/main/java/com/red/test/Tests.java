package com.red.test;

import com.red.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

public class Tests {
    Configuration configure = new Configuration().configure();
    SessionFactory sessionFactory = configure.buildSessionFactory();
    Session session = sessionFactory.openSession();
    @Test
    public void test(){


        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setAge(18);
        session.save(user);
        session.beginTransaction().commit();
        session.close();
    }


    public void selectTest(String username){
        String hql = "from User where username='"+username+"'";
        Query query = session.createQuery(hql);
        User user = (User) query.uniqueResult();
        System.out.println(user);
        session.close();
    }

    @Test
    public void selectUser(){
        String username = "admin' or '1'='1";
        selectTest(username);
    }
}
