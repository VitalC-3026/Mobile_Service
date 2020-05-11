package com.mobile_service.controller;

import com.mobile_service.entity.*;
import com.mobile_service.service.PackageService;
import com.mobile_service.service.UserScene;
import com.mobile_service.service.UserService;
import com.mobile_service.serviceImpl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
public class ServiceController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private PackageService packageService;
    private UserScene userScene = new UserScene();


    @RequestMapping(value="/homepageRedirect", method = RequestMethod.GET)
    public ModelAndView homepageRedirect(@NotNull HttpServletRequest request, HttpSession session){

        String choice = request.getParameter("choice");
        System.out.println(choice);
        switch (choice){
            case "注册": {
                String[] Cards = userService.randomCreateCards();
                session.setAttribute("Cards", Cards);
                return new ModelAndView("redirect:mobile_service/register.jsp");
            }
            case "使用移动":{
                String message="";
                session.setAttribute("message",message);
                return new ModelAndView("redirect:mobile_service/use.jsp");
            }
            default: {
                return new ModelAndView("redirect:mobile_service/login.jsp");
            }
        }
    }

   @RequestMapping(value = "/loginCheck")
   public String loginCheck(@NotNull HttpServletRequest request, HttpSession session) throws MyException {
        String submit = request.getParameter("submit");
        if(submit.equals("3")){
            String resetPassword = "密码重置";
            session.setAttribute("resetPassword", resetPassword);
            return "redirect:mobile_service/use.jsp";
        }
        String Telephone = request.getParameter("Telephone");
        boolean res = userServiceImpl.isUserExist(Telephone);
        if(res){
            if(submit.equals("4")){
                String checkCode = request.getParameter("checkCode");
                String newPassword = request.getParameter("password1");
                String newPassword1 = request.getParameter("password2");
                String exception = userService.resetPassword(Telephone,newPassword,newPassword1,checkCode);
                if(exception != null){
                    session.setAttribute("message", exception);
                    return "redirect:mobile_service/message1.jsp";
                }
                else{
                    session.setAttribute("message","密码修改成功，请重新登录");
                    return "redirect:mobile_service/message1.jsp";
                }
            }
           String password = request.getParameter("password");
           if(userServiceImpl.getPassword(Telephone).equals(password)){
               User user = userService.getUser(Telephone);
               session.setAttribute("user", user);
               return "redirect:mobile_service/useService.jsp";
           }
           else{
               String message = "密码错误";
               session.setAttribute("message",message);
               return "redirect:mobile_service/message1.jsp";
           }
        }
        else{
           String message = "用户不存在";
           session.setAttribute("message",message);
           return "redirect:mobile_service/message.jsp";
        }
   }

    @RequestMapping(value = "/Service", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView Service(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        try{
            String info = request.getParameter("submit");
            User user = (User) session.getAttribute("user");

            String MonthlyPackage = request.getParameter("MonthlyPackage");
            if(MonthlyPackage!=null){
                switch (MonthlyPackage) {
                    case "1":
                        userService.changePackage(user, "话痨套餐");
                        break;
                    case "2":
                        userService.changePackage(user, "网虫套餐");
                        break;
                    case "3":
                        userService.changePackage(user, "超人套餐");
                        break;
                }
            }

            String DataPack = request.getParameter("DataPack");
            if(DataPack!=null){
                switch (DataPack) {
                    case "1":
                        userService.addDataPack(user,1);
                        break;
                    case "2":
                        userService.addDataPack(user,2);
                        break;
                    case "3":
                        userService.addDataPack(user, 3);
                        break;
                }
            }

            String LifeService = request.getParameter("LifeService");
            if(LifeService!=null){
                if(LifeService.equals("1")){
                    LifeService = "视频VIP";
                }
                else{
                    LifeService = "音乐VIP";
                }
                userService.addServiceLife(user, LifeService);
            }

            String deposit = request.getParameter("deposit");
            if(!deposit.equals("")){
                double money = 0;
                try{
                    money = Double.parseDouble(deposit);
                }
                catch(Exception e){
                    session.setAttribute("error", e.getMessage());
                }
                userService.Deposit(user, money);
            }

            if(info != null){
                switch(info){
                    case "0":{
                        userService.deleteUser(user.getTelephone());
                        return new ModelAndView("redirect:mobile_service/bye.jsp");
                    }
                    case "1":{
                        String bill =userService.printBill(user);
                        session.setAttribute("bill", bill);
                        return new ModelAndView("redirect:mobile_service/useService.jsp");
                    }
                    case "2":{
                        String left = userService.printLeft(user);
                        session.setAttribute("left",left);
                        return new ModelAndView("redirect:mobile_service/useService.jsp");
                    }
                    case "3":{
                        List<Deposit> depositList = userServiceImpl.getDepositRecord(user.getTelephone());
                        session.setAttribute("depositList", depositList);
                        return new ModelAndView("redirect:mobile_service/depositRecord.jsp");
                    }
                    case "4":{
                        List<Consumption> consumptionList = userServiceImpl.getConsumptionRecord(user.getTelephone());
                        session.setAttribute("consumptionList", consumptionList);
                        return new ModelAndView("redirect:mobile_service/consumptionRecord.jsp");
                    }
                    case "5":{
                        return new ModelAndView("redirect:mobile_service/homepage.jsp");
                    }
                }
            }
            session.setAttribute("message","办理业务成功");
            return new ModelAndView("redirect:mobile_service/message2.jsp");
        }catch(Exception e){
            session.setAttribute("exception", e.getMessage());
            return new ModelAndView("redirect:mobile_service/homepage.jsp");
        }
    }

    @RequestMapping(value = "/useCheck")
    public String use(@NotNull HttpServletRequest request, HttpSession session){
        String submit = request.getParameter("submit");
        if("0".equals(submit)){
            return "redirect:mobile_service/homepage.jsp";
        }
        String Telephone = request.getParameter("Telephone");
        if(userServiceImpl.isUserExist(Telephone)){
            String message = "登录成功";
            session.setAttribute("message", message);
            Map setScene = userScene.getSetting();
            session.setAttribute("setScene", setScene);
            User user = userService.getUser(Telephone);
            session.setAttribute("user", user);
            return "redirect:mobile_service/use.jsp";
        }
        else{
            String message = "登录失败";
            session.setAttribute("message",message);
            return "redirect:mobile_service/use.jsp";
        }
    }

    @RequestMapping(value = "/UserScene")
    public String userScene(HttpSession session, HttpServletRequest request){
        try{
            User user = (User) session.getAttribute("user");
            String submit = request.getParameter("submit");
            String scene = request.getParameter("scene");
            switch(submit){
                case "2":{
                    Map setScene = userScene.getSetting();
                    session.setAttribute("setScene", setScene);
                    return "redirect:mobile_service/use.jsp";
                }
                case "3":{
                    String result = "尊敬的用户"+user.getTelephone()+":\n";
                    switch(scene){
                        case "1":{
                            String Telephone = request.getParameter("toTelephone");
                            String call = request.getParameter("Call");
                            if(!call.equals("")){
                                call = userService.useMobileService(user, "通话", Double.parseDouble(call));
                            }
                            if(call!=null){
                                if(Telephone!=null){
                                    result += "您刚刚正在与" + Telephone + "通话。\n";
                                }
                                result += call;
                            }else{
                                result += "您的套餐不支持通话。\n";
                            }
                            break;
                        }
                        case "2":{
                            String surf = request.getParameter("Surf");
                            if(!surf.equals("")){
                                surf = userService.useMobileService(user, "流量", Double.parseDouble(surf));
                            }
                            if(surf!=null){
                                result += surf;
                            }
                            else{
                                result += "您的套餐不支持上网。\n";
                            }
                        }
                        case "3":{
                            String Telephone = request.getParameter("toTelephone");
                            String text = request.getParameter("Text");
                            if(!text.equals("")){
                                text = userService.useMobileService(user, "短信", Double.parseDouble(text));
                            }
                            if(text!=null){
                                if(Telephone!=null){
                                    result += "您刚刚正在与" + Telephone + "发短信。\n";
                                }
                                result += text;
                            }
                            else{
                                result += "您的套餐不支持发短信。\n";
                            }
                        }
                    }
                    session.setAttribute("ServiceInfo", result);
                    return "redirect:mobile_service/use.jsp";
                }
                default:{
                    Map setScene = (Map)session.getAttribute("setScene");
                    String ServiceInfo = "尊敬的用户"+user.getTelephone()+":\n";
                    ServiceInfo += userService.useMobileService(user, setScene);
                    session.setAttribute("ServiceInfo", ServiceInfo);
                    return "redirect:mobile_service/use.jsp";
                }
            }
        }
        catch(Exception e){
            String exception = e.getMessage();
            session.setAttribute("exception", exception);
            return "redirect:mobile_service/homepage.jsp";
        }
    }

    @RequestMapping(value = "/loginPage", method = {RequestMethod.GET,RequestMethod.POST})
    public String loginPage(@NotNull HttpServletRequest request, HttpSession session){
        String error = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String Telephone = request.getParameter("Telephone");
        String PackageName = request.getParameter("PackageName");
        String deposit = request.getParameter("deposit");
        try{
            if(username.equals("")){
                error = "用户名不能为空";
                session.setAttribute("error", error);
            }
            else if(password.equals("") || password2.equals("")){
                error = "密码不能为空";
                session.setAttribute("error", error);
            }
            else if(!password.equals(password2)){
                error = "两次密码输入不一致";
                session.setAttribute("error", error);
            }
            else if(Telephone == null){
                error = "请您选一个手机号码";
                session.setAttribute("error", error);
            }
            else if(PackageName == null){
                error = "请您选择一个套餐";
                session.setAttribute("error", error);
            }
            else if(deposit == null || deposit.equals("")){
                error = "请您充值";
                session.setAttribute("error", error);
            }
            else{
                String[] Cards = (String[]) session.getAttribute("Cards");
                Telephone =  Cards[Integer.parseInt(Telephone)];
                MonthlyPackage monthlyPackage = userService.instantiate(Integer.parseInt(PackageName));
                monthlyPackage.setTelephone(Telephone);
                User user = new User();
                user.setUserName(username);
                user.setPassword(password);
                user.setTelephone(Telephone);
                user.setMonthlyPackage(monthlyPackage);
                double balance = Double.parseDouble(deposit) - monthlyPackage.getPack_fee();
                user.setPackageName(monthlyPackage.getPackageName());
                user.setBalance(balance);
                Deposit depositRecord = new Deposit();
                depositRecord.setMoney(balance);
                depositRecord.setTelephone(Telephone);
                userService.addNewUser(user, depositRecord);
                String message = "注册成功";
                session.setAttribute("message", message);
                String userInfo = userService.printForNew(user);
                session.setAttribute("userInfo", userInfo);
                return "redirect:mobile_service/homepage.jsp";
            }
            return "redirect:mobile_service/register.jsp";
        }catch(Exception e){
            String exception = e.getMessage();
            session.setAttribute("exception", exception);
            return "redirect:mobile_service/homepage.jsp";
        }
    }

    @RequestMapping(value = "/packageInfoDisplay")
    public String packageDisplayPage(@NotNull HttpSession session){
        Map<String, PackageInfo> map = packageService.getInfoMap();
        session.setAttribute("talkingTooMuch", map.get("话痨套餐"));
        session.setAttribute("netWorm", map.get("网虫套餐"));
        session.setAttribute("superman", map.get("超人套餐"));
        session.setAttribute("out", map.get("超出套餐计费"));
        return "redirect:mobile_service/packageInfo.jsp";

    }
}
