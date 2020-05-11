package com.mobile_service.service;

import java.util.*;

public class UserScene {
    private List<String> Scene = new ArrayList<>();
    private List<String> Mode = new ArrayList<>();
    private List<Double> Flow = new ArrayList<>();


    public UserScene(){
        String[][] scene ={
                {"问候客户，谁知其如此难缠 通话90分钟","通话","90"},
                {"通知朋友手机换号 发送短信100条","短信","100"},
                {"参与环境保护实施方案问卷调查 发送短信5条","短信","5"},
                {"看4k高清粤语版国庆阅兵 上网用流量","流量","5"},
                {"发微博控诉作业很多的Java课程，被网友教育要好好学习 上网用流量","流量","1"},
                {"和父母聊天 通话40分钟","通话","40"}
        };
        for(String[] string: scene){
            Scene.add(string[0]);
            Mode.add(string[1]);
            Flow.add(Double.parseDouble(string[2]));
        }
    }

    public void setFlow(List<Double> flow) {
        this.Flow = flow;
    }
    public void setMode(List<String> mode) {
        Mode = mode;
    }
    public void setScene(List<String> scene) {
        Scene = scene;
    }

    public List<String> getScene(){
        return Scene;
    }
    public List<String> getMode(){
        return Mode;
    }
    public List<Double> getFlow(){
        return Flow;
    }


    public Map<String, java.io.Serializable> getSetting(){
        Random r = new Random();
        int UserMode = r.nextInt(6);
        Map<String, java.io.Serializable> setting = new HashMap<String, java.io.Serializable>();
        while(UserMode < 0 || UserMode > 5){
            UserMode = r.nextInt(6);
        }
        setting.put("scene", this.Scene.get(UserMode));
        setting.put("mode", this.Mode.get(UserMode));
        setting.put("flow", this.Flow.get(UserMode));
        return setting;

    }

}

