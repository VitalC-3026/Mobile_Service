package com.mobile_service.service;

import com.mobile_service.entity.MonthlyPackage;
import com.mobile_service.entity.PackageInfo;
import com.mobile_service.serviceImpl.PackageServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.Map;

public class PackageService {

    private PackageServiceImpl packageServiceImpl;
    private SqlSessionTemplate sqlSession;

    public PackageService(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSessionTemplate;
    }
    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    public void setPackageServiceImpl(PackageServiceImpl packageServiceImpl) {
        this.packageServiceImpl = packageServiceImpl;
    }
    public PackageServiceImpl getPackageServiceImpl() {
        return packageServiceImpl;
    }

    private Map<String, PackageInfo> packageInfoMap = new HashMap<>();
    public Map<String, PackageInfo> getInfoMap(){
        getPackageInfoMap();
        return packageInfoMap;
    }
    private void getPackageInfoMap() {
        String[] packageName = {"话痨套餐", "网虫套餐", "超人套餐", "超出套餐计费"};
        for(String string: packageName){
            PackageInfo packageInfo  = packageServiceImpl.UpdatePackageInformation(string);
            packageInfoMap.put(string, packageInfo);
        }
    }
    public void modifyPackageInfo(@org.jetbrains.annotations.NotNull MonthlyPackage monthlyPackage){
        getPackageInfoMap();
        String packageName = monthlyPackage.getPackageName();
        PackageInfo packageInfo = packageInfoMap.get(packageName);
        monthlyPackage.setPack_call(packageInfo.getCall());
        monthlyPackage.setPack_data(packageInfo.getData());
        monthlyPackage.setPack_message(packageInfo.getText());
        monthlyPackage.setPack_fee(packageInfo.getFee());
        monthlyPackage.setFee(packageInfo.getFee());
        packageInfo = packageInfoMap.get("超出套餐计费");
        monthlyPackage.setOut_call(packageInfo.getCall());
        monthlyPackage.setOut_data(packageInfo.getData());
        monthlyPackage.setOut_message(packageInfo.getText());
    }
    void modifyOutPackageInfo(@NotNull MonthlyPackage monthlyPackage){
        getPackageInfoMap();
        String packageName = monthlyPackage.getPackageName();
        PackageInfo packageInfo = packageInfoMap.get(packageName);
        monthlyPackage.setPack_fee(packageInfo.getFee());
        packageInfo = packageInfoMap.get("超出套餐计费");
        monthlyPackage.setOut_call(packageInfo.getCall());
        monthlyPackage.setOut_data(packageInfo.getData());
        monthlyPackage.setOut_message(packageInfo.getText());
    }

}
