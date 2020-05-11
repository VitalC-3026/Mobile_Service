package com.mobile_service.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class MonthlyPackage {
    String Telephone;
    String PackageName;
    double phone_call = 0;
    double extra_call = 0;
    double pack_call = 0;
    double data = 0;
    double extra_data = 0;
    double pack_data = 0;
    double message = 0;
    double extra_message = 0;
    double pack_message = 0;
    double fee = 0;
    double extra_fee = 0;
    double pack_fee = 0;
    //超出套餐的计费
    double out_call = 0;
    double out_data = 0;
    double out_message = 0;

    /*public MonthlyPackage(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring-mybatis.xml");
        PackageInfo packageInfo = (PackageInfo) ctx.getBean("outPackage");
        out_call = packageInfo.getOut_call();
        out_message = packageInfo.getOut_message();
        out_data = packageInfo.getData();
    }*/

    public abstract String printPackage();
    public abstract String printPackageUse();
    public abstract String printPackageLeft(User s);
    public abstract void DeleteAllInfo();

    public abstract void setPackageName(String packageName);
    public abstract void setTelephone(String telephone);

    public abstract void setPhone_call(double phone_call);
    public abstract void setExtra_call(double extra_call);
    public abstract void setPack_call(double pack_call);
    public abstract void setData(double data);
    public abstract void setExtra_data(double extra_data);
    public abstract void setPack_data(double pack_data);
    public abstract void setMessage(double message);
    public abstract void setExtra_message(double extra_message);
    public abstract void setPack_message(double pack_message);
    public abstract void setFee(double fee);
    public abstract void setExtra_fee(double extra_fee);
    public abstract void setPack_fee(double pack_fee);
    public abstract void setOut_call(double out_call);
    public abstract void setOut_data(double out_data);
    public abstract void setOut_message(double out_message);

    public abstract String getPackageName();
    public abstract String getTelephone();
    public abstract double getPhone_call();
    public abstract double getExtra_call();
    public abstract double getOut_call();
    public abstract double getPack_call();
    public abstract double getData();
    public abstract double getExtra_data();
    public abstract double getOut_data();
    public abstract double getPack_data();
    public abstract double getMessage();
    public abstract double getExtra_message();
    public abstract double getOut_message();
    public abstract double getPack_message();
    public abstract double getFee();
    public abstract double getExtra_fee();
    public abstract double getPack_fee();
}
