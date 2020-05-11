package com.mobile_service.entity;


public class PackageInfo {
    private String packageName;
    private double call;
    private double text;
    private double data;
    private double fee;
    private double out_call;
    private double out_data;
    private double out_message;

    public double getCall() {
        return call;
    }

    public double getData() {
        return data;
    }

    public double getFee() {
        return fee;
    }

    public double getText() {
        return text;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getOut_message() {
        return out_message;
    }

    public double getOut_call() {
        return out_call;
    }

    public double getOut_data() {
        return out_data;
    }

    public void setCall(double call) {
        this.call = call;
    }

    public void setData(double data) {
        this.data = data;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setText(double text) {
        this.text = text;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setOut_call(double out_call) {
        this.out_call = out_call;
    }

    public void setOut_message(double out_message) {
        this.out_message = out_message;
    }

    public void setOut_data(double out_data) {
        this.out_data = out_data;
    }


}
