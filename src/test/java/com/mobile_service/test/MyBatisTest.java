package com.mobile_service.test;

import com.mobile_service.dao.UserDao;
import com.mobile_service.entity.Consumption;
import com.mobile_service.entity.Deposit;
import com.mobile_service.entity.TalkingTooMuch;
import com.mobile_service.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.jws.soap.SOAPBinding;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


public class MyBatisTest {
    public static void main(String[] args) throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        SqlSession session = factory.openSession();

        UserDao userDao = session.getMapper(UserDao.class);

        /*LinkedList<Deposit> deposits = userDao.getDepositRecord("18902810777");

        while(deposits.size()>0){
            Deposit deposit = deposits.remove();
            System.out.println(deposit.getTelephone()+"充值"+deposit.getMoney()+"元，充值时间:"+deposit.getDepositTime());
        }

        LinkedList<Consumption> consumptions = userDao.getConsumptionRecord("18902810777");

        while(consumptions.size()>0){
            Consumption consumption = consumptions.remove();
            System.out.println(consumption.getTelephone()+consumption.getType()+consumption.getFlow());
        }

        int res = userDao.deleteUser("18969586867");
        System.out.println(res);*/

        User user = new User();
        user.setPackageNumber('1');
        user.setBalance(23.4);
        user.setPassword("111");
        user.setTelephone("13802463001");
        user.setUserName("pap");
        user.setPackageName("话痨套餐");
        TalkingTooMuch talkingTooMuch = new TalkingTooMuch();
        user.setMonthlyPackage(talkingTooMuch);
        userDao.createUser(user);

        session.close();
        inputStream.close();
    }
}
