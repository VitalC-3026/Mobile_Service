package com.mobile_service.controller;

import com.mobile_service.serviceImpl.UserServiceImpl;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private UserServiceImpl userService;

    @Contract(pure = true)
    public IndexController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("hello,SpringMVC!");

        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(@RequestParam(value="username",required=false) String username){
        System.out.println("welcome," + username);
        String password = userService.getPassword("18902810777");
        System.out.println(password);

        return "index";
    }
}
