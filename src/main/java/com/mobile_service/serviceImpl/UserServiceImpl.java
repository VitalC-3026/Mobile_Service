package com.mobile_service.serviceImpl;

import com.mobile_service.dao.UserDao;
import com.mobile_service.entity.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserDao {
    private SqlSessionTemplate sqlSession;

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public boolean isUserExist(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        User user =  userDao.getUser(Telephone);
        if(user != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public User getUser(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getUser(Telephone);
    }

    @Override
    public TalkingTooMuch getPackageUsage1(String Telephone){
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getPackageUsage1(Telephone);
    }

    @Override
    public NetWorm getPackageUsage2(String Telephone){
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getPackageUsage2(Telephone);
    }

    @Override
    public Superman getPackageUsage3(String Telephone){
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getPackageUsage3(Telephone);
    }

    @Override
    public String getUserPackageName(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getUserPackageName(Telephone);
    }

    @Override
    public void createUser(User user) {
        userDao = sqlSession.getMapper(UserDao.class);
        userDao.createUser(user);
    }

    @Override
    public String getPassword(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getPassword(Telephone);
    }

    @Override
    public int resetPassword(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.resetPassword(map);
    }

    @Override
    public double getBalance(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getBalance(Telephone);
    }

    @Override
    public void createPackageUsage(MonthlyPackage monthlyPackage){
        userDao = sqlSession.getMapper(UserDao.class);
        userDao.createPackageUsage(monthlyPackage);
    }

    @Override
    public int updatePackageUsage(MonthlyPackage monthlyPackage) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.updatePackageUsage(monthlyPackage);
    }

    @Override
    public List<Deposit> getDepositRecord(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getDepositRecord(Telephone);
    }

    @Override
    public List<Consumption> getConsumptionRecord(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.getConsumptionRecord(Telephone);
    }

    @Override
    public int addConsumptionRecord(Consumption con) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.addConsumptionRecord(con);
    }

    @Override
    public int addDepositRecord(Deposit dep) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.addDepositRecord(dep);

    }

    @Override
    public int deleteUser(String Telephone) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.deleteUser(Telephone);
    }

    @Override
    public int addLifeService(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        int update = userDao.addLifeService(map);
        return update;
    }

    @Override
    public int changePackageName(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.changePackageName(map);
    }

    @Override
    public int changePackage(MonthlyPackage monthlyPackage){
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.changePackage(monthlyPackage);
    }

    @Override
    public int addDataPack(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.addDataPack(map);
    }

    @Override
    public int deposit(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.deposit(map);
    }

    @Override
    public int consume(Map map) {
        userDao = sqlSession.getMapper(UserDao.class);
        return userDao.consume(map);
    }


}
