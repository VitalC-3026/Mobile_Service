package com.mobile_service.entity;

public class User {
    private String UserName;
    private String password;
    private String Telephone;
    private String packageName;
    private double Balance;
    private String lifeService;
    private MonthlyPackage monthlyPackage;
    private char packageNumber;

    public String getTelephone() {
        return Telephone;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPassword() {
        return password;
    }

    public char getPackageNumber() {
        return packageNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public String getLifeService() {
        return lifeService;
    }

    public String getUserName() {
        return UserName;
    }

    public MonthlyPackage getMonthlyPackage() {
        return monthlyPackage;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setBalance(double balance) {
        this.Balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLifeService(String lifeService) {
        this.lifeService = lifeService;
    }

    public void setMonthlyPackage(MonthlyPackage monthlyPackage) { this.monthlyPackage = monthlyPackage; }
    public void setUserName(String userName) { UserName = userName; }

    public void setPackageNumber(char packageNumber) {
        this.packageNumber = packageNumber;
    }

    public double UseTelephone(String mode, double flow){
        double fee = 0;

        if(monthlyPackage instanceof TalkingTooMuch){
            if(mode.equals("通话")){
                ((TalkingTooMuch) monthlyPackage).call(flow);
                fee = monthlyPackage.fee;
            }
            if(mode.equals("流量")){
                System.out.println("您无法完成此操作");
            }
            if(mode.equals("短信")){
                ((TalkingTooMuch) monthlyPackage).text((int)flow);
                fee = monthlyPackage.fee;
            }
            fee = fee - monthlyPackage.getPack_fee();
            return fee;
        }
        if(monthlyPackage instanceof NetWorm){
            if(mode.equals("通话") || mode.equals("短信")){
                System.out.println("您无法完成此操作");
            }
            if(mode.equals("流量")){
                ((NetWorm) monthlyPackage).surf(flow);
                fee = monthlyPackage.fee;
            }
            fee = fee - monthlyPackage.getPack_fee();
            return fee;
        }
        if(monthlyPackage instanceof Superman){
            if(mode.equals("通话")){
                ((Superman) monthlyPackage).call(flow);
                fee = monthlyPackage.fee;
            }
            if(mode.equals("短信")){
                ((Superman) monthlyPackage).text((int)flow);
                fee = monthlyPackage.fee;
            }
            if(mode.equals("流量")){
                ((Superman) monthlyPackage).surf(flow);
                fee = monthlyPackage.fee;
            }
            fee = fee - monthlyPackage.getPack_fee();
            return fee;
        }
        return fee;

    }
}
