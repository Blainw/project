package cn.edu.wtu.wdm.controller;

import cn.edu.wtu.wdm.model.po.Lecturer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ToPage")
public class ToPageController {

    //去注册页面
    @RequestMapping(value="/register.action")
    public String ToRegister(){
        return "register";
    }

    //去主页
    @RequestMapping(value = "/homepage.action")
    public String ToHomePage(HttpServletRequest request){
        return "homepage";
    }

    //去登陆页面
    @RequestMapping(value = "/login.action")
    public String ToLogin(){
        return "login";
    }

    //去修改页面
    @RequestMapping(value = "/update.action")
    public String ToUpdate(){
        return "update";
    }

    //去管理员页面
    @RequestMapping(value = "/management.action")
    public String ToManagement(){
        return "management";
    }

}
