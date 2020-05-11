package com.mobile_service.dao;

import com.mobile_service.entity.*;
import org.springframework.stereotype.Repository;
import sun.nio.ch.Net;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    public boolean isUserExist(String Telephone);
    public User getUser(String Telephone);
    public void createUser(User user);
    public String getPassword(String Telephone);
    public int resetPassword(Map map);
    public double getBalance(String Telephone);
    public int deleteUser(String Telephone);

    public List<Deposit> getDepositRecord(String Telephone);
    public List<Consumption> getConsumptionRecord(String Telephone);
    public int addConsumptionRecord(Consumption con);
    public int addDepositRecord(Deposit de);
    public int addLifeService(Map map);//Telephone service
    public int addDataPack(Map map);//Telephone data
    public int deposit(Map map);//Telephone money
    public int consume(Map map);//Telephone money

    public int changePackageName(Map map);//Telephone PackageName
    public int changePackage(MonthlyPackage monthlyPackage);
    public String getUserPackageName(String Telephone);
    public TalkingTooMuch getPackageUsage1(String Telephone);
    public NetWorm getPackageUsage2(String Telephone);
    public Superman getPackageUsage3(String Telephone);
    public void createPackageUsage(MonthlyPackage monthlyPackage);
    public int updatePackageUsage(MonthlyPackage monthlyPackage);
}
