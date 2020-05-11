package com.mobile_service.service;

import com.mobile_service.entity.*;
import com.mobile_service.serviceImpl.UserServiceImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class UserService {

    private UserServiceImpl userServiceImplement;
    public UserService(PackageService packageService, SqlSessionTemplate sqlSessionTemplate) {
        this.packageService = packageService;
        sqlSession = sqlSessionTemplate;
    }
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private PackageService packageService;


    public UserService(SqlSessionTemplate sqlSessionTemplate) {
    }

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        userServiceImplement = userServiceImpl;
    }
    public UserServiceImpl getUserServiceImplement() {
        return userServiceImplement;
    }
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    private MonthlyPackage getPackageUse(String Telephone) {
        String packageName = userServiceImplement.getUserPackageName(Telephone);
        switch (packageName){
            case "话痨套餐":{
                TalkingTooMuch talkingTooMuch = userServiceImplement.getPackageUsage1(Telephone);
                packageService.modifyOutPackageInfo(talkingTooMuch);
                return talkingTooMuch;
            }
            case "网虫套餐":{
                NetWorm netWorm = userServiceImplement.getPackageUsage2(Telephone);
                packageService.modifyOutPackageInfo(netWorm);
                return netWorm;
            }
            case "超人套餐":{
                Superman superman = userServiceImplement.getPackageUsage3(Telephone);
                packageService.modifyOutPackageInfo(superman);
                return superman;
            }
        }
        return null;
    }
    private MonthlyPackage instantiate(String PackageName){
        switch(PackageName){
            case "网虫套餐":{
                NetWorm netWorm = new NetWorm();
                packageService.modifyPackageInfo(netWorm);
                return netWorm;
            }
            case "超人套餐":{
                Superman superman =  new Superman();
                packageService.modifyPackageInfo(superman);
                return superman;
            }
            default:{
                TalkingTooMuch talkingTooMuch = new TalkingTooMuch();
                packageService.modifyPackageInfo(talkingTooMuch);
                return talkingTooMuch;
            }
        }
    }
    public MonthlyPackage instantiate(int PackageNumber){
        switch(PackageNumber){
            case 2:{
                NetWorm netWorm = new NetWorm();
                packageService.modifyPackageInfo(netWorm);
                return netWorm;
            }
            case 3:{
                Superman superman =  new Superman();
                packageService.modifyPackageInfo(superman);
                return superman;
            }
            default:{
                TalkingTooMuch talkingTooMuch = new TalkingTooMuch();
                packageService.modifyPackageInfo(talkingTooMuch);
                return talkingTooMuch;
            }
        }
    }

    public void addNewUser(User new_customer, Deposit deposit){
        if(userServiceImplement.getUser(new_customer.getTelephone())==null){
            userServiceImplement.createUser(new_customer);
            userServiceImplement.createPackageUsage(new_customer.getMonthlyPackage());
            userServiceImplement.addDepositRecord(deposit);
        }
        else{
            System.out.println("Duplicate entry!");
        }
    }

    public String printForNew(User user){
        String newUserInfo = "注册成功！\n";
        newUserInfo += "卡号:"+user.getTelephone()+"，用户名:"+user.getUserName()+"，当前余额:"+user.getBalance()+"元。\n";
        newUserInfo += user.getMonthlyPackage().printPackage();
        return newUserInfo;
    }

    public boolean isExist(String Telephone){
        return userServiceImplement.isUserExist(Telephone);
    }

    public boolean isPasswordCorrect(String Telephone, String password){
        String key = userServiceImplement.getPassword(Telephone);
        return key.equals(password);
    }

    public String resetPassword(String Telephone, String password, String password2, String checkinCode) throws MyException {
        try{
            if(isExist(Telephone)){
                if(checkinCode.equals("123456")){
                    if(password.equals(password2)){
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("password", password);
                        map.put("Telephone", Telephone);
                        userServiceImplement.resetPassword(map);
                        return null;
                    }
                    else{
                        throw new MyException("两次密码输入不一致");
                    }

                }
                else{
                    throw new MyException("验证码不正确");
                }
            }
            else{
                throw new MyException("手机号码不存在");
            }
        }catch(MyException mine){
            return mine.printMessage();
        }catch(Exception e){
            return e.getMessage();
        }
    }


    public User getUser(String Telephone){
        User user = userServiceImplement.getUser(Telephone);
        MonthlyPackage monthlyPackage = getPackageUse(Telephone);
        user.setMonthlyPackage(monthlyPackage);
        return user;
    }

    public void deleteUser(String Telephone){
        userServiceImplement.deleteUser(Telephone);
    }
    //用户充值
    public void Deposit(User user, double money){
        Map<String, java.io.Serializable> map = new HashMap<String, java.io.Serializable>();
        map.put("Telephone", user.getTelephone());
        map.put("money", money);
        userServiceImplement.deposit(map);
        Deposit deposit = new Deposit();
        deposit.setTelephone(user.getTelephone());
        deposit.setMoney(money);
        userServiceImplement.addDepositRecord(deposit);
    }
    public void UserDeposit(Deposit deposit){
        Map<String, java.io.Serializable> map = new HashMap<String, java.io.Serializable>();
        map.put("Telephone", deposit.getTelephone());
        map.put("money", deposit.getMoney());
        userServiceImplement.deposit(map);
        userServiceImplement.addDepositRecord(deposit);
    }
    //套餐花费
    public void UserConsume(Deposit consume){
        Map<String, java.io.Serializable> map = new HashMap<String, java.io.Serializable>();
        map.put("Telephone", consume.getTelephone());
        map.put("money", consume.getMoney());
        userServiceImplement.consume(map);
    }
    //更换套餐
    public void changePackage(User user, String packageName){
        MonthlyPackage monthlyPackage = instantiate(packageName);
        monthlyPackage.setTelephone(user.getTelephone());
        userServiceImplement.changePackage(monthlyPackage);

        Map<String, String> Name = new HashMap<>();
        Name.put("Telephone", user.getTelephone());
        Name.put("PackageName", monthlyPackage.getPackageName());
        userServiceImplement.changePackageName(Name);

        Deposit consume = new Deposit();
        consume.setMoney(monthlyPackage.getPack_fee());
        consume.setTelephone(user.getTelephone());
        UserConsume(consume);

        System.out.println("套餐更换成功！");
    }

    //打印本月账单
    public String printBill(User s){
        DecimalFormat formatDouble = new DecimalFormat("0.00");
        MonthlyPackage p = s.getMonthlyPackage();
        String PackageUse = null;
        if(p instanceof TalkingTooMuch){
            TalkingTooMuch talkingTooMuch = (TalkingTooMuch) p;
            PackageUse=talkingTooMuch.printPackageUse();
        }
        if(p instanceof NetWorm){
            NetWorm netWorm = (NetWorm) p;
            PackageUse=netWorm.printPackageUse();
        }
        if(p instanceof Superman){
            Superman superman = (Superman) p;
            PackageUse=superman.printPackageUse();
        }

        if(s.getBalance() >= 0){
            String format_left = formatDouble.format(s.getBalance());
            PackageUse+="本月余额:"+format_left+"元\n";
        }
        else{
            PackageUse+="本月余额:"+0+"元,  ";
            String format_left = formatDouble.format(s.getBalance());
            PackageUse+="本月欠费:"+format_left+"元。";
        }
        return PackageUse;
    }
    //打印套餐余量
    public String printLeft(User s){
        String left = null;
        MonthlyPackage p = s.getMonthlyPackage();
        if(p instanceof TalkingTooMuch){
            TalkingTooMuch talkingTooMuch = (TalkingTooMuch) p;
            left = talkingTooMuch.printPackageLeft(s);
        }
        if(p instanceof NetWorm){
            NetWorm netWorm = (NetWorm) p;
            left = netWorm.printPackageLeft(s);
        }
        if(p instanceof Superman){
            Superman superman = (Superman) p;
            left = superman.printPackageLeft(s);
        }
        return left;
    }
    //打印充值清单
    public String printDeposit(User user){
        List<Deposit> deposits = userServiceImplement.getDepositRecord(user.getTelephone());
        String record ="------------"+ user.getTelephone()+"用户充值清单------------\n";
        for(Deposit deposit:deposits){
            record += "充值"+deposit.getMoney()+"元 充值时间:"+deposit.getDepositTime()+'\n';
        }
        return record;
    }
    //打印消费清单
    public String printConsumption(User user){
        List<Consumption> consumptions = userServiceImplement.getConsumptionRecord(user.getTelephone());
        String record = "------------"+ user.getTelephone()+"用户消费清单------------\n";
        for(Consumption consumption:consumptions){
            record+="消费类型:"+consumption.getMode()+" "+consumption.getFlow()+'\n';
            switch(consumption.getMode()){
                case "通话":{
                    record +="分钟";
                    break;
                }
                case "流量":{
                    record+="MB";
                    break;
                }
                case "短信":{
                    record+="条";
                }
            }
            record+=" 消费时间:" + consumption.getSave_time() + '\n';
        }
        return record;
    }
    //资费说明
    public void PrintPackageInfo()throws Exception{
        FileReader fr = new FileReader("资费说明.txt");
        BufferedReader br = new BufferedReader(fr);
        try{

            String str;
            while((str = br.readLine()) != null)
            {
                System.out.println(str);
            }

        }catch(Exception e){
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        br.close();
        fr.close();
    }
    //判断用户的余额是否充足
    private boolean BalanceEnough(User user, double num){
        double balance = userServiceImplement.getBalance(user.getTelephone());
        return !(balance >= num);
    }
    //添加生活服务
    public void addServiceLife(User user, String lifeService){
        User temp = userServiceImplement.getUser(user.getTelephone());
        if(temp.getLifeService()!=null && temp.getLifeService().contains(lifeService)){
            System.out.print("您已经办理此业务，不可重复办理！");
        }
        else{
            Map<String, String> map = new HashMap<>();
            map.put("Telephone", user.getTelephone());
            map.put("service", lifeService);
            userServiceImplement.addLifeService(map);
            Deposit deposit = new Deposit();
            deposit.setTelephone(user.getTelephone());
            switch(lifeService){
                case "视频VIP":{
                    deposit.setMoney(18);
                    break;
                }
                case "音乐VIP":{
                    deposit.setMoney(10);
                    break;
                }
            }
            UserConsume(deposit);
        }
    }
    public void addDataPack(User user, int data){
        Deposit consume = new Deposit();
        consume.setTelephone(user.getTelephone());
        consume.setMoney(data*10);
        UserConsume(consume);

        Map<String, java.io.Serializable> map = new HashMap<>();
        map.put("Telephone", user.getTelephone());
        map.put("data", (double)data*1024);
        userServiceImplement.addDataPack(map);
    }


    //使用移动服务
    public String useMobileService(User user, Map userScene){
        String mode = (String) userScene.get("mode");
        double flow = (double) userScene.get("flow");
        String result;
        switch(mode){
            case "通话":{
                result = talk(user, mode, flow);
                break;
            }
            case "流量":{
                result = surf(user, mode, flow);
                break;
            }
            case "短信":{
                result = text(user, mode, flow);
                break;
            }
            default: {
                result = null;
            }
        }
        return result;
    }
    public String useMobileService(User user, String mode, double flow){
        String result;
        switch(mode){
            case "通话":{
                result = talk(user, mode, flow);
                break;
            }
            case "流量":{
                result = surf(user, mode, flow);
                break;
            }
            case "短信":{
                result = text(user, mode, flow);
                break;
            }
            default:{
                result = null;
            }
        }
        return result;
    }
    private String talk(User user, String mode, double flow){
        String result;
        Consumption consumption = new Consumption();
        consumption.setTelephone(user.getTelephone());
        consumption.setMode(mode);
        consumption.setFlow(flow);
        MonthlyPackage monthlyPackage = getPackageUse(user.getTelephone());
        double curr_fee = 0;
        if(monthlyPackage instanceof NetWorm){
            result = "您的套餐不支持此项消费。\n";
            return null;
        }
        else if(monthlyPackage instanceof TalkingTooMuch){
            TalkingTooMuch talkingTooMuch = (TalkingTooMuch) monthlyPackage;
            curr_fee = user.UseTelephone(mode, flow);
            if (talkingTooMuch.OutOfCall(flow) == 0) {
                result = "您套餐里的通话时长已被用完，正在按超额通话价格扣费！\n";
            } else if (talkingTooMuch.OutOfCall(flow) > 0) {
                result = "您已经通话"+talkingTooMuch.OutOfCall(flow)+"分钟，现套餐里的通话时长已被用完，正在按超额通话价格扣费！\n";
            }
            else{
                result = "您已经成功通话"+flow+"分钟！\n";
            }
            talkingTooMuch.setFee(curr_fee+talkingTooMuch.getPack_fee());
            talkingTooMuch.setExtra_fee(curr_fee);
        }
        else{
            Superman superman = (Superman) monthlyPackage;
            curr_fee = user.UseTelephone(mode, flow);
            if (superman.OutOfCall(flow) == 0) {
                result = "您套餐里的通话时长已被用完，正在按超额通话价格扣费！\n";
            } else if (superman.OutOfCall(flow) > 0) {
                result = "您已经通话"+superman.OutOfCall(flow)+"分钟，现套餐里的通话时长已被用完，正在按超额通话价格扣费！\n";
            }
            else{
                result = "您已经成功通话"+flow+"分钟！\n";
            }
            superman.setFee(curr_fee+superman.getPack_fee());
            superman.setExtra_fee(curr_fee);
        }
        if(BalanceEnough(user, curr_fee)){
            result += "您的余额不足，请尽快充值！\n";
        }
        Deposit deposit = new Deposit();
        deposit.setMoney(curr_fee);
        deposit.setTelephone(user.getTelephone());
        UserConsume(deposit);
        userServiceImplement.addConsumptionRecord(consumption);
        userServiceImplement.updatePackageUsage(user.getMonthlyPackage());
        return result;
    }
    private String surf(User user, String mode, double flow){
        String result;
        Consumption consumption = new Consumption();
        consumption.setTelephone(user.getTelephone());
        consumption.setMode(mode);
        consumption.setFlow(flow);
        MonthlyPackage monthlyPackage = getPackageUse(user.getTelephone());
        double curr_fee = 0;
        if(monthlyPackage instanceof TalkingTooMuch){
            result = "您的套餐不支持此项消费。\n";
            return null;
        }
        else if(monthlyPackage instanceof NetWorm){
            NetWorm netWorm = (NetWorm) user.getMonthlyPackage();
            curr_fee = user.UseTelephone(mode, flow);
            if(netWorm.OutOfSurf(flow) == 0){
                result = "您套餐里的流量已被用完，正在按超额流量价格扣费！\n";
            }
            else if(netWorm.OutOfSurf(flow) > 0){
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String used_data = decimalFormat.format(netWorm.OutOfSurf(flow));
                result = "您已使用了"+used_data+"GB的套餐内流量，现超过套餐使用限额，正在按超额流量价格扣费\n";
                flow = netWorm.OutOfSurf(flow);
            }
            else{
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String data = decimalFormat.format(flow);
                result = "您已经成功使用了"+data+"GB流量！\n";
            }
            netWorm.setFee(curr_fee + netWorm.getPack_fee());
            netWorm.setExtra_fee(curr_fee);
        }
        else{
            Superman superman = new Superman();
            curr_fee = user.UseTelephone(mode, flow);
            if(superman.OutOfSurf(flow) == 0){
                result = "您套餐里的流量已被用完，正在按超额流量价格扣费！\n";
            }
            else if(superman.OutOfSurf(flow) > 0){
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String used_data = decimalFormat.format(superman.OutOfSurf(flow));
                result = "您已使用了"+used_data+"GB的套餐内流量，现超过套餐使用限额，正在按超额流量价格扣费\n";
            }
            else{
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String data = decimalFormat.format(flow);
                result = "您已经成功使用了"+data+"GB流量！\n";
            }
            superman.setFee(curr_fee+superman.getPack_fee());
            superman.setExtra_fee(curr_fee);
        }
        if(BalanceEnough(user, curr_fee)){
            result += "您的余额不足，请尽快充值！\n";
        }
        Deposit deposit = new Deposit();
        deposit.setMoney(curr_fee);
        deposit.setTelephone(user.getTelephone());
        UserConsume(deposit);
        userServiceImplement.addConsumptionRecord(consumption);
        userServiceImplement.updatePackageUsage(user.getMonthlyPackage());
        return result;
    }
    private String text(User user, String mode, double flow){
        String result;
        Consumption consumption = new Consumption();
        consumption.setTelephone(user.getTelephone());
        consumption.setMode(mode);
        consumption.setFlow(flow);
        MonthlyPackage monthlyPackage = getPackageUse(user.getTelephone());
        double curr_fee = 0;
        if(monthlyPackage instanceof NetWorm){
            result = "您的套餐不支持此项消费。\n";
            return null;
        }
        else if(monthlyPackage instanceof TalkingTooMuch){
            TalkingTooMuch talkingTooMuch = (TalkingTooMuch) monthlyPackage;
            curr_fee = user.UseTelephone(mode, flow);
            if (talkingTooMuch.OutOfText((int) flow) == 0) {
                result = "您套餐里的短信条数已被用完，正在按超额发短信价格扣费！\n";
            } else if (talkingTooMuch.OutOfText((int) flow) > 0) {
                result = "您已经使用套餐内短信"+talkingTooMuch.OutOfText((int)flow)+"条，现套餐里的短信条数已被用完，正在按超额发短信价格扣费！\n";
            }
            else{
                result = "您已经成功发送"+(int)flow+"条短信!\n";
            }
            talkingTooMuch.setFee(curr_fee+talkingTooMuch.getPack_fee());
            talkingTooMuch.setExtra_fee(curr_fee);
        }
        else{
            Superman superman = (Superman) monthlyPackage;
            curr_fee = user.UseTelephone(mode, flow);
            if (superman.OutOfText((int)flow) == 0) {
                result = "您套餐里的短信条数已被用完，正在按超额发短信价格扣费！\n";
            } else if (superman.OutOfText((int)flow) > 0) {
                result = "您已经使用套餐内短信"+superman.OutOfText((int)flow)+"条，现套餐里的短信条数已被用完，正在按超额发短信价格扣费！\n";
            }
            else{
                result = "您已经成功发送"+(int)flow+"条短信!\n";
            }
            superman.setFee(curr_fee+superman.getPack_fee());
            superman.setExtra_fee(curr_fee);
        }
        if(BalanceEnough(user, curr_fee)){
            result += "您的余额不足，请尽快充值！\n";
        }
        Deposit deposit = new Deposit();
        deposit.setMoney(curr_fee);
        deposit.setTelephone(user.getTelephone());
        UserConsume(deposit);
        userServiceImplement.addConsumptionRecord(consumption);
        userServiceImplement.updatePackageUsage(user.getMonthlyPackage());
        return result;
    }

    //自动生成电话号码
    public String[] randomCreateCards(){
        HashMap<Integer, String> randomTelephone = new HashMap<>();
        String[] Cards = new String[9];
        StringBuilder telephone;
        Random r = new Random();
        for(int i = 0; i < 9; i++){
            telephone = new StringBuilder("189");
            for(int j = 0; j < 8; j++){
                int bit = r.nextInt(10);
                telephone.append(bit);
            }
            if(randomTelephone.containsValue(telephone.toString())){
                i--;
            }
            else{
                randomTelephone.put(i, telephone.toString());
                Cards[i] = telephone.toString();
            }
        }
        return Cards;
    }



}
