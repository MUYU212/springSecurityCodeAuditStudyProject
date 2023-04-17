package com.red.test;

import com.red.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Tests {
    @Test
    public void test(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setAge(18);
        session.save(user);
        session.beginTransaction().commit();
        session.close();
    }
}
