package com.mobile_service.web;

import com.mobile_service.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String Telephone = request.getParameter("Telephone");
            Integer packageNumber = Integer.valueOf(request.getParameter("PackageName"));
            char number = (char)(packageNumber+48);
            Double deposit = Double.valueOf(request.getParameter("deposit"));

            User user = new User();
            user.setUserName(userName);
            user.setTelephone(Telephone);
            user.setPassword(password);
            user.setPackageNumber(number);
            user.setBalance(deposit);
            //有记录写进数据库，后台通过他选的套餐再进行扣钱
            out.println("register");
            /*request.setAttribute("message","注册成功");
            request.getRequestDispatcher("/message.jsp").forward(request,response);*/
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message","注册失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }

}
