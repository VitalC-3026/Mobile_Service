/*
package com.mobile_service.service;

import com.mobile_service.entity.*;
import com.mobile_service.service.UserScene;
import com.mobile_service.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Scanner;

public class ServiceHall{
    static private ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "spring-mybatis.xml");
    static private UserService userService = (UserService) ctx.getBean("userService");

    static public int getInput(Scanner scanner, String input, int up, int down) throws Exception{

        int count = 1, output = 0, i = 0;
        boolean int_number = false, valid_number = false;
        for(; i < input.length(); i++ ){
            if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                break;
            }
        }
        if(i == input.length()){

            int_number = true;
            try{
                output = Integer.parseInt(input);
            }catch(Exception e)
            {
                System.out.println("输入有误，请输入数字"+down+"-"+up);
                count++;
            }
            if(output >= down && output <= up){
                valid_number = true;
            }
        }
        while (!int_number || !valid_number) {
            i = 0;
            System.out.println("输入有误，请输入数字"+down+"-"+up);
            input = scanner.next();
            count++;
            int_number = false;
            valid_number = false;
            for(; i < input.length(); i++ ){
                if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                    break;
                }
            }
            if(i == input.length()){
                int_number = true;
                try{
                    output = Integer.parseInt(input);
                }catch(Exception e)
                {
                    System.out.println("输入有误，请输入数字"+down+"-"+up);
                    count++;
                }
                if(output >= down && output <= up){
                    valid_number = true;
                }
            }
            if(count > 10){
                break;
            }
            //throw new MyException("输入数据有误，请输入"+down+"-"+up);
        }
        if(count > 10) {
            System.out.println("多次输入错误，系统已自动退出");
            return -1;
        }
        return output;
    }
    static private void RootMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("**********欢迎来到龙龙移动业务大厅**********");
            System.out.println("1、用户注册 2、用户登录 3、使用龙龙 4、退出系统");
            System.out.println("请输入您的选择: ");
            String first = scanner.next();
            int first_menu = 0;
            */
/*对输入进行正确性判断*//*

            first_menu = getInput(scanner,first,4,1);
            if(first_menu == -1){
                return;
            }
            */
/*根据输入进入不同的子菜单中*//*

            if(first_menu == 1){
                int i = FirstMenu_1();
                if(i == 0){
                    continue;
                }
                if(i == -1){
                    return;
                }
            }
            if(first_menu == 2){
                int i = FirstMenu_2();
                if(i == 0){
                    continue;
                }
                if(i == -1){
                    return;
                }
            }

            if(first_menu == 3) {
                int i = FirstMenu_3();
                if(i == 0){
                    continue;
                }
                if(i == -1){
                    return;
                }
            }

            if(first_menu == 4){
                System.out.println("谢谢，欢迎下次光临！");
                break;
            }
        }
    }

    static private int FirstMenu_1() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********龙龙为您挑了9个电话号码，请您选择**********");
        */
/*随机生成9个电话号码并打印*//*

        HashMap<Integer, String> randomTele = new HashMap<Integer, String>();
        String tele;
        Random r = new Random();
        for(int i = 0; i < 9; i++){
            tele = "189";
            for(int j = 0; j < 8; j++){
                int bit = r.nextInt(10);
                tele = tele + bit;
            }
            if(randomTele.containsValue(tele)){
                i--;
            }
            else{
                randomTele.put(i,tele);
            }
        }
        for(int i = 0; i < 9; i++){
            String random_telephone = randomTele.get(i);
            if((i + 1) % 3 == 0){
                System.out.print((i+1)+". "+random_telephone+'\n');
            }
            else{
                System.out.print((i+1)+". "+random_telephone+" ");
            }
        }
        String sign_in = scanner.next();
        int telephone = 0;
        telephone = getInput(scanner, sign_in, 9, 1);
        User new_customer = new User();
        new_customer.setTelephone(randomTele.get(telephone - 1));
        System.out.println("请输入您的用户名:");
        String name = scanner.next();
        new_customer.setUserName(name);
        System.out.println("请设置您的手机账户密码:");
        String new_password = scanner.next();
        System.out.println("请再输入一遍:");
        String new_password_a = scanner.next();
        int count = 0;//当输入密码前后多次不匹配则返回上一级
        while(!new_password.equals(new_password_a)){
            System.out.println("两次输入的密码不一致,请重新输入");
            new_password_a = scanner.next();
            count ++;
            if(count > 3){
                break;
            }
        }
        if(new_password.equals(new_password_a)){
            new_customer.setPassword(new_password);
            System.out.println("注册成功");
        }
        else if(count > 3){
            System.out.println("密码多次输入不匹配，请重新注册");
            return 0;
        }
        System.out.println("1、话痨套餐，2、网虫套餐，3、超人套餐。请选择套餐(输入序号):");
        String pack = scanner.next();
        int pack_choice  = 0;
        pack_choice = getInput(scanner, pack, 3, 1);
        pack_choice = (char) (pack_choice + 48);

        System.out.println("请输入预存话费:");
        double money = scanner.nextDouble();
        if(money >= new_customer.getMonthlyPackage().getPack_fee()){
            System.out.println("预存成功");

        }
        else{
            System.out.print("预存金额无法支付当前选择套餐的费用，请输入超过当前套餐包月");
            System.out.print(new_customer.getMonthlyPackage().getPack_fee());
            System.out.print("元的话费，请重新输入\n");
            money = scanner.nextDouble();
        }

        new_customer.setMonthlyPackage(userService.instantiate((char)pack_choice));
        new_customer.setPackageName(new_customer.getMonthlyPackage().getPackageName());

        Deposit deposit = new Deposit();
        deposit.setMoney(money);
        deposit.setTelephone(new_customer.getTelephone());
        userService.UserDeposit(deposit);
        deposit.setMoney(new_customer.getMonthlyPackage().getPack_fee());
        userService.UserConsume(deposit);
        userService.addNewUser(new_customer);
        userService.printForNew(new_customer);

        return 0;

    }
    static private int FirstMenu_2() throws Exception{
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        System.out.println("请输入您的手机号: ");
        String phone_number = scanner.next();
        boolean res = userService.isExist(phone_number);
        if(!res){
            System.out.println("您还没有龙龙移动的电话卡，请尽快注册哟！");
            return 0;
        }
        String in_password;
        System.out.println("请输入您的密码: (忘记密码请输入#)");
        //加一个找回密码的功能
        in_password = scanner.next();
        if(userService.isPasswordCorrect(phone_number, in_password)){
            System.out.println("输入密码成功！");
            User curr_customer = userService.getUser(phone_number);
            while(true){
                System.out.println("**********龙龙很高兴能为您服务**********");
                System.out.println("1、业务办理 2、查询服务 3、办理退网 4、退出系统");
                int service = scanner.nextInt();

                try{
                    if(service > 4 || service < 1){
                        throw new MyException("输入超界");
                    }
                }catch(MyException e){
                    e.getMessage();
                    System.out.println("请输入数字1-4");
                    return 0;
                }
                switch (service){
                    case 1: {
                        int i = SecondMenu_1(curr_customer);
                        if(i == 1){
                            continue;
                        }
                        if(i == -1){
                            return -1;
                        }
                        //回退主界面
                        return 0;
                    }
                    case 2:{
                        int i = SecondMenu_2(curr_customer);
                        if(i == 1){
                            continue;
                        }
                        if(i == -1){
                            return -1;
                        }
                        //回退主界面
                        return 0;
                    }
                    case 3:{
                        System.out.println("**********龙龙很难过，你真的要选择退网吗(输入y/n)**********");
                        String back_off = scanner.next();
                        if(back_off.equals("y")){
                            userService.deleteUser(phone_number);
                            System.out.println("感谢您的使用，希望未来您还能继续使用龙龙移动哦！");
                            //退出系统
                            return -1;
                        }
                        else{
                            //回退主界面
                            return 0;
                        }
                    }
                    case 4:{
                        System.out.println("谢谢，欢迎下次光临！");
                        //退出系统
                        return -1;
                    }
                }
            }

        }
        else if(in_password.equals("#")){
            System.out.println("**********密码重置**********");
            System.out.println("请确认您的电话号码:"+phone_number);
            System.out.println("确定(y/n)");
            String certify = scanner.next();
            if(certify.equals("y")){
                System.out.println("请输入验证码:");
                String verify_code = scanner.next();
                if(verify_code.equals("123456")){
                    System.out.println("请输入您的新密码:");
                    String new_password = scanner.next();
                    System.out.println("请再输入一遍:");
                    String new_password_a = scanner.next();
                    if(new_password.equals(new_password_a)){
                        userService.resetPassword(phone_number,new_password);
                        flag = true;
                        System.out.println("密码修改成功");
                    }
                    else{
                        System.out.println("密码修改失败");
                        //退回主界面
                        return 0;
                    }
                }
                else{
                    System.out.println("验证码不正确");
                    //退回主界面
                    return 0;
                }
            }
            else{
                System.out.println("手机号码不匹配");
                //退回主界面
                return 0;
            }
            if(flag){
                System.out.println("请重新登录");
                return 0;//主界面
            }

        }
        else{
            System.out.println("密码输入不正确");
            return 0;
        }
        return 0;
    }
    static private int FirstMenu_3() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********欢迎使用龙龙移动**********");
        System.out.println("请输入手机号:");
        String phone_number = scanner.next();
        boolean res = userService.isExist(phone_number);

        if(!res){
            System.out.println("号码不存在");
            //返回上一级
            return 0;
        }
        else {
            User curr_customer = userService.getUser(phone_number);
            System.out.println("系统预设使用龙龙示例:");
            UserScene userScene = (UserScene) ctx.getBean("userSetting");
            userScene.getSetting();
            String mode = userScene.getMode();
            double flow = userScene.getFlow();
            System.out.println(userScene.getScene());
            if (mode.equals("通话")) {
               userService.talk(curr_customer,mode,flow);
            }

            if(mode.equals("短信")){
                userService.text(curr_customer,mode,flow);
            }
            if(mode.equals("流量")){
                userService.surf(curr_customer,mode,flow);
            }
            System.out.println("用户自定义模式:");
            MonthlyPackage m = curr_customer.getMonthlyPackage();
            if(m instanceof TalkingTooMuch){
                System.out.println("1、通话 2、发短信");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:{
                        System.out.println("请拨号:");
                        String call_phoneNo = scanner.next();
                        System.out.println("请输入通话时间:");
                        int time = scanner.nextInt();
                        System.out.println("用户"+curr_customer.getTelephone()+"给用户"+call_phoneNo+"拨打了"+time+"分钟电话");
                        userService.talk(curr_customer, "通话", time);
                        break;
                    }
                    case 2:{
                        System.out.println("请拨号:");
                        String call_phoneNo = scanner.next();
                        System.out.println("请输入发送短信条数");
                        int message = scanner.nextInt();
                        System.out.println("用户"+curr_customer.getTelephone()+"给用户"+call_phoneNo+"发送了"+message+"条短信");
                        userService.text(curr_customer,"短信", message);
                        break;
                    }
                }
            }
            if(m instanceof NetWorm){
                System.out.println("请输入您的上网耗费流量");
                int data = scanner.nextInt();
                System.out.println("用户"+curr_customer.getTelephone()+"上网使用了了"+data+"MB流量");
                userService.surf(curr_customer,"流量",data);
            }
            if(m instanceof Superman){
                Superman p = (Superman) m;
                System.out.println("1、通话 2、发短信 3、上网");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:{
                        System.out.println("请拨号:");
                        String call_phoneNo = scanner.next();
                        System.out.println("请输入通话时间:");
                        int time = scanner.nextInt();
                        System.out.println("用户"+curr_customer.getTelephone()+"给用户"+call_phoneNo+"拨打了"+time+"分钟电话");
                        userService.talk(curr_customer, "通话", time);
                        break;
                    }
                    case 2:{
                        System.out.println("请拨号:");
                        String call_phoneNo = scanner.next();
                        System.out.println("请输入发送短信条数");
                        int message = scanner.nextInt();
                        System.out.println("用户"+curr_customer.getTelephone()+"给用户"+call_phoneNo+"发送了"+message+"条短信");
                        userService.text(curr_customer,"短信", message);
                        break;
                    }
                    case 3:{
                        System.out.println("请输入您的上网耗费流量");
                        int data = scanner.nextInt();
                        System.out.println("用户"+curr_customer.getTelephone()+"上网使用了了"+data+"MB流量");
                        userService.surf(curr_customer,"流量",data);
                        break;
                    }
                }
            }
        }
        return 0;
    }

    static private int SecondMenu_1(User curr_customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("**********龙龙很高兴能为您办理业务**********");
            System.out.println("1、主套餐 2、流量包 3、生活服务 4、话费充值 ");
            int business = scanner.nextInt();
            try{
                if(business > 4 || business < 1){
                    System.out.println("输出超界");
                    throw new MyException("输入超界");
                }
            }catch(MyException e){
                e.getMessage();
                //返回第一级菜单
                return 1;
            }

            switch (business) {
                case 1: {
                    int i = ThirdMenu_1(curr_customer);
                    if(i == 2){
                        continue;
                    }
                    if(i == 1){
                        return 1;
                    }
                    break;
                }
                case 2: {
                    int i = ThirdMenu_2(curr_customer);
                    if(i == 2){
                        continue;
                    }
                    if(i == 1){
                        return 1;
                    }
                    break;
                }
                case 3: {
                    int i = ThirdMenu_3(curr_customer);
                    if(i == 1){
                        return 1;
                    }
                    break;
                }
                case 4: {
                    int i = ThirdMenu_4(curr_customer);
                    if(i==2){
                        continue;
                    }
                    if(i == -1){
                        return -1;
                    }
                    break;
                }
            }
        }
    }
    static private int SecondMenu_2(User curr_customer) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********龙龙很高兴能帮您查询**********");
        System.out.println("1、本月账单打印 2、套餐余量查询 3、缴费记录查询 4、消费记录查询 5、资费说明");
        int information = scanner.nextInt();
        try{
            if(information < 1 || information > 5){
                System.out.println("输出超界");
                throw new MyException("输出超界，返回上一级");
            }
        }catch (MyException e){
            e.getMessage();
            return 1;//返回一级菜单
        }
        switch(information){
            case 1:{
                userService.printBill(curr_customer);
                break;
            }
            case 2:{
                userService.printLeft(curr_customer);
                break;
            }
            case 3:{
                userService.printDeposit(curr_customer);
                break;
            }
            case 4:{
                userService.printConsumption(curr_customer);
                break;
            }
            case 5:{
                userService.PrintPackageInfo();
                break;
            }
        }
        //返回一级菜单
        return 1;
    }

    static private int ThirdMenu_1(User curr_customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********龙龙移动有以下几种套餐供您选择**********");
        System.out.println("1、话痨套餐 2、网虫套餐 3、超人套餐 ");
        int Package = scanner.nextInt();
        try{
            if(Package < 1 || Package > 3){
                throw new MyException("输出超界，返回上一级");
            }
        }catch (MyException e){
            e.getMessage();
            return 2;
        }
        MonthlyPackage curr_package = curr_customer.getMonthlyPackage();
        switch (Package) {
            case 1: {
                TalkingTooMuch talking = new TalkingTooMuch();
                talking.printPackage();
                if (curr_package.getPackageName() == null) {
                    System.out.println("您还没有选择套餐，您需要选择这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,talking.getPack_fee())){
                            userService.changePackage(curr_customer, "话痨套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                } else if (curr_package.getPackageName().equals("话痨套餐")) {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您正在查看您自己的套餐明细。");
                    return 1;
                } else {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您需要更换成这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,talking.getPack_fee())){
                            userService.changePackage(curr_customer,"话痨套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                }
                break;
            }
            case 2: {
                NetWorm net = new NetWorm();
                net.printPackage();
                if (curr_package.getPackageName() == null) {
                    System.out.println("您还没有选择套餐，您需要选择这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,net.getPack_fee())){
                            //curr_customer.setMonthly_package('2');
                           userService.changePackage(curr_customer, "网虫套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                } else if (curr_package.getPackageName().equals("网虫套餐")) {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您正在查看您自己的套餐明细。");
                    return 1;

                } else {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您需要更换成这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,net.getPack_fee())){
                            userService.changePackage(curr_customer,"网虫套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                }
                break;
            }
            case 3: {
                Superman superman = new Superman();
                superman.printPackage();
                if (curr_package.getPackageName() == null) {
                    System.out.println("您还没有选择套餐，您需要选择这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,superman.getPack_fee())){
                            userService.changePackage(curr_customer, "超人套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                } else if (curr_package.getPackageName().equals("超人套餐")) {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您正在查看您自己的套餐明细。");
                    return 1;

                } else {
                    System.out.print("您现在的套餐是:" + curr_package.getPackageName());
                    System.out.println(",您需要更换成这个套餐吗？(y/n)");
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        if(userService.BalanceEnough(curr_customer,superman.getPack_fee())){
                            userService.changePackage(curr_customer, "超人套餐");
                            return 1;
                        }
                        else{
                            System.out.println("您的余额不足，请先充值。");
                            return 2;
                        }
                    }
                }
                break;
            }
        }
        return 0;
    }
    static private int ThirdMenu_2(User curr_customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********龙龙超大流量包等你来拿**********");
        System.out.println("1、1GB 2、2GB 3、3GB ");
        int datapack = scanner.nextInt();
        try{
            if(datapack < 1 || datapack > 3){
                throw new MyException("输出超界，返回上一级");
            }
        }catch (MyException e){
            e.getMessage();
            return 2;
        }
        if(curr_customer.getMonthlyPackage() instanceof NetWorm){
            ((NetWorm) curr_customer.getMonthlyPackage()).setDataPack(datapack);
            if(userService.BalanceEnough(curr_customer,datapack*10)){
                userService.addDataPack(curr_customer, datapack);
            }
            else{
                System.out.println("您的余额不足，请先充值。");
                return 2;
            }
        }
        else if(curr_customer.getMonthlyPackage() instanceof Superman){
            ((Superman) curr_customer.getMonthlyPackage()).setDataPack(datapack);
            if(userService.BalanceEnough(curr_customer,datapack*10)){
                userService.addDataPack(curr_customer, datapack);
            }
            else{
                System.out.println("您的余额不足，请先充值。");
                return 2;
            }
        }
        else{
            System.out.println("您的套餐不支持超大流量包");
            return 1;
        }
        return 1;
    }
    static private int ThirdMenu_3(User curr_customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********龙龙为您的生活增光添彩**********");
        System.out.println("1、视频VIP 2、音乐VIP");
        int entertaining = scanner.nextInt();
        try {
            if (entertaining < 1 || entertaining > 3) {
                throw new MyException("输出超界，返回上一级");
            }
        } catch (MyException e) {
            e.getMessage();
            return 1;
        }
        String lifeService = null;
        switch(entertaining){
            case 1:{
                lifeService = "视频VIP";
                break;
            }
            case 2:{
                lifeService = "音乐VIP";
                break;
            }
        }
        userService.addServiceLife(curr_customer, lifeService);
        return 1;
    }
    static private int ThirdMenu_4(User curr_customer) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********话费不足，记得要及时充值噢，请输入充值金额**********");
        double money = scanner.nextDouble();
        int count = 1;
        while (money < 50) {
            System.out.println("至少充值50元");
            money = scanner.nextDouble();
            count++;
            if (count > 10) {
                break;
            }
        }
        if (count > 10) {
            System.out.println("多次输入错误，系统自动退出");
            //退出系统
            return -1;
        } else {
            Deposit deposit = new Deposit();
            deposit.setTelephone(curr_customer.getTelephone());
            deposit.setMoney(money);
            userService.UserDeposit(deposit);
            System.out.println("充值成功！");
            //返回二级菜单
            return 2;
        }
    }

    static public void main(String[] args) throws Exception {

        RootMenu();
        //customers.print_all_customers();
    }
}

*/
