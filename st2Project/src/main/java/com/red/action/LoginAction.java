package com.red.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.red.pojo.User;

public class LoginAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    public String login(){

        return "login";
    }
    public String logon(){
        System.out.println(user.getUsername()+"====="+user.getPassword());
        if (user.getPassword().equals("123456")){
            return "success";
        }
        return "error";
    }
    public User getModel() {
        return user;
    }
}
