package com.mobile_service.entity;

import java.sql.Timestamp;

public class Consumption {
    private String Telephone;
    private String Mode;
    private double Flow;
    private Timestamp save_time;

    public void setFlow(double Flow) {
        this.Flow = Flow;
    }

    public void setSave_time(Timestamp save_time) {
        this.save_time = save_time;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setMode(String Type) {
        this.Mode = Type;
    }

    public Timestamp getSave_time() {
        return save_time;
    }

    public double getFlow() {
        return Flow;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getMode() { return Mode; }
}
