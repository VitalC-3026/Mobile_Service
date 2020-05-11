package com.mobile_service.dao;

import com.mobile_service.entity.PackageInfo;


public interface PackageDao {
    /*public void CreatePackageInformation(PackageInfo p);//将资费说明加入到数据库中
    public void CreateOutPackageInformation(PackageInfo p);//将超出套餐的计费加入到数据库中*/
    public PackageInfo UpdatePackageInformation(String packageName);//更改资费说明(运营商价格调整) 常用
    public PackageInfo UpdateOutPackageInformation(String packageName);//更改超出套餐的计费 常用
}
