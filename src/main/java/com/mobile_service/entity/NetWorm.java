package com.mobile_service.entity;

import com.mobile_service.service.PackageService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class NetWorm extends MonthlyPackage implements Surf,DataPack{

    public NetWorm() {
        PackageName = "网虫套餐";

    }

    public void setDataPack(int i){
        pack_data += i * 1024;
    }

    public void printToText(BufferedWriter bw) throws IOException {
        String[] packageInfo = {"套餐类型:网虫套餐\n","通话时长:0分钟\n","上网流量:3GB\n",
                "短信条数:0条\n","月资费:68元\n","——————————————————————————————————\n"
        };
        for(int i = 0; i < packageInfo.length; i++){
            bw.append(packageInfo[i]);
            bw.flush();
        }
    }
    public void call(double CallTime){
        System.out.println("该套餐不包含通话服务，龙龙将按照超出套餐包的价格收费。");
        phone_call += CallTime;
        fee += CallTime*0.2;
    }
    public void surf(double Data){
        if(data>=pack_data){
            System.out.println("您使用的流量已超过套餐包含的流量，龙龙将按照超出套餐包的价格收费。");
            data += Data;
            extra_data += Data;
            fee += Data*out_data;
            extra_fee = fee - pack_fee;
        }
        else if(data+Data>=pack_data){
            double temp = data + Data - pack_data;
            System.out.println("您使用的流量已超过套餐包含的流量，龙龙将按照超出套餐包的价格收费。");
            data += Data;
            extra_data += temp;
            fee += temp*out_data;
            extra_fee = fee - pack_fee;
        }
        else{
            data += Data;
        }
    }

    @Override
    public double OutOfSurf(double Data) {
        DecimalFormat formatDouble = new DecimalFormat("0.00");
        if(data > pack_data){
            return 0;
        }
        if(data + Data > pack_data){
            if(data + Data > pack_data){
                return Double.parseDouble(formatDouble.format((pack_data - data)/1024));
            }
        }
        return -1;
    }

    public void text(int Message){
        System.out.println("该套餐不包含发送短信服务，龙龙将按照超出套餐包的价格收费。");
        message += Message;
        fee += Message*0.1;
    }

    public String printPackage(){
        String out = "套餐类型:"+PackageName+"\n";
        out += "通话时长:"+pack_call+"分钟\n";
        out += "上网流量:"+pack_data+"MB\n";
        out += "短信条数:"+pack_message+"条\n";
        out += "--------------------\n";
        out += "月资费:"+pack_fee+"元\n";
        out += "--------------------\n";
        out += "超出套餐的计费:\n";
        out += "通话:"+out_call+"元/分钟\n";
        out += "上网流量:"+out_data+"元/MB\n";
        out += "短信:"+out_message+"元/条\n";
        return out;
    }
    public String printPackageUse(){
        DecimalFormat formatDouble = new DecimalFormat("0.00");
        String PackageUse = Telephone+"用户的"+PackageName+"使用情况:\n";
        PackageUse+="本套餐无通话服务，超出套餐的通话时长:"+phone_call+"分钟\n";
        String format_data = formatDouble.format(data/1024);
        PackageUse+="上网流量:"+format_data+"GB\n";
        if(extra_data > 0){
            String format_extra_data = formatDouble.format(extra_data/1024);
            PackageUse+="超出套餐的流量:"+format_extra_data+"GB\n";
        }
        PackageUse+="本套餐无短信服务，超出套餐的短信条数:"+message+"条\n";
        String format_fee = formatDouble.format(extra_fee);
        PackageUse+="已提前扣费68元，本月额外消费:"+format_fee+"元\n";
        String total_fee = formatDouble.format(fee);
        PackageUse+="合计:"+total_fee+"元\n";
        return PackageUse;
    }
    public String printPackageLeft(User s){
        DecimalFormat formatDouble = new DecimalFormat("0.00");
        String PackageLeft = s.getTelephone()+"用户的"+PackageName+"剩余:\n";
        if(extra_data > 0){
            PackageLeft+="套餐的流量剩余:"+0+"MB\n";
        }
        else{
            String format_left_data = formatDouble.format((extra_data)/1024);
            PackageLeft+="套餐的流量剩余:"+format_left_data+"GB\n";
        }
        return PackageLeft;
    }
    public void DeleteAllInfo(){
        phone_call = 0;
        data = 0;
        message = 0;
        fee = 68;
    }

    public void setPackageName(String packageName) { PackageName = packageName; }
    public void setTelephone(String telephone) { Telephone = telephone; }

    public void setPhone_call(double phone_call) { this.phone_call = phone_call; }
    public void setExtra_call(double extra_call){ this.extra_call = extra_call; }
    public void setPack_call(double pack_call){ this.pack_call = pack_call; }
    public void setData(double data) { this.data = data; }
    public void setExtra_data(double extra_data){ this.extra_data = extra_data;}
    public void setPack_data(double pack_data){ this.pack_data = pack_data;}
    public void setMessage(double message){ this.message = message; }
    public void setExtra_message(double extra_message){ this.extra_message = extra_message;}
    public void setPack_message(double pack_message) { this.pack_message = pack_message; }
    public void setFee(double fee) { this.fee = fee; }
    public void setExtra_fee(double extra_fee){ this.extra_fee = extra_fee;}
    public void setPack_fee(double pack_fee){ this.pack_fee = pack_fee;}
    public void setOut_call(double out_call) { this.out_call = out_call; }
    public void setOut_data(double out_data){ this.out_data = out_data;}
    public void setOut_message(double out_message){ this.out_message = out_message;}

    public String getPackageName() {
        return PackageName;
    }
    public String getTelephone() {
        return Telephone;
    }
    public double getPhone_call() {
        return phone_call;
    }
    public double getExtra_call() {
        return extra_call;
    }
    public double getOut_call() {
        return out_call;
    }
    public double getPack_call() {
        return pack_call;
    }
    public double getData() {
        return data;
    }
    public double getExtra_data() {
        return extra_data;
    }
    public double getOut_data() {
        return out_data;
    }
    public double getPack_data() {
        return pack_data;
    }
    public double getMessage() {
        return message;
    }
    public double getExtra_message() {
        return extra_message;
    }
    public double getOut_message() {
        return out_message;
    }
    public double getPack_message() {
        return pack_message;
    }
    public double getFee() {
        return fee;
    }
    public double getExtra_fee() {
        return extra_fee;
    }
    public double getPack_fee() { return pack_fee; }
}