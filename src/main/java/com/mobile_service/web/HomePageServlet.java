package com.mobile_service.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends javax.servlet.http.HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setCharacterEncoding("UTF-8");

            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String Telephone = request.getParameter("Telephone");
            //电话号码去数据库中查询，如果查询成功的话就跳转页面

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
