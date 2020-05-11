package com.mobile_service.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Deposit {
    private String Telephone;
    private Timestamp DepositTime;
    private double Money;

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setSave_time(Timestamp save_time) {
        this.DepositTime = save_time;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public String getTelephone() {
        return Telephone;
    }

    public Timestamp getDepositTime() {
        return DepositTime;
    }

    public double getMoney() {
        return Money;
    }
}
