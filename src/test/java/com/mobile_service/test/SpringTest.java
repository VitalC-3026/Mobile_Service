package com.mobile_service.test;

import com.mobile_service.entity.*;
import com.mobile_service.service.PackageService;
import com.mobile_service.service.UserService;
import com.mobile_service.serviceImpl.PackageServiceImpl;
import com.mobile_service.serviceImpl.UserServiceImpl;
import org.junit.Test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.nio.ch.Net;


public class SpringTest {
    private Logger logger = Logger.getLogger(SpringTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring-mybatis.xml");
        PackageService packageService = (PackageService) ctx.getBean("packageService");
        PackageServiceImpl packageServiceImpl = (PackageServiceImpl) ctx.getBean("packageServiceImpl");
        NetWorm netWorm = new NetWorm();
        packageService.modifyPackageInfo(netWorm);
        String s = netWorm.printPackage();
        Superman superman = new Superman();
        packageService.modifyPackageInfo(superman);
        s += superman.printPackage();
        TalkingTooMuch talkingTooMuch = new TalkingTooMuch();
        packageService.modifyPackageInfo(talkingTooMuch);
        s += talkingTooMuch.printPackage();
        System.out.println(s);

    }

    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring-mybatis.xml");
        PackageInfo packageInfo = (PackageInfo) ctx.getBean("outPackage");
        Superman superman = new Superman();
        System.out.println(superman.getOut_message());
        System.out.println(packageInfo.getOut_call());
        System.out.println(superman.getOut_call());
        System.out.println(packageInfo.getOut_data());
        System.out.println(packageInfo.getOut_message());
    }
}

