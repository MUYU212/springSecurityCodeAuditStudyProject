package com.red.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.red.pojo.User;

public class LoginAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    public String login(){
        System.out.println(user.getUsername()+"====="+user.getPassword());
        return "login";
    }
    public User getModel() {
        return user;
    }
}
