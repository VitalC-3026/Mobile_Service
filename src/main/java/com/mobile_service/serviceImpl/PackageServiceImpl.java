package com.mobile_service.serviceImpl;

import com.mobile_service.dao.PackageDao;
import com.mobile_service.dao.UserDao;
import com.mobile_service.entity.Consumption;
import com.mobile_service.entity.Deposit;
import com.mobile_service.entity.PackageInfo;
import com.mobile_service.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class PackageServiceImpl implements PackageDao {

    @Autowired
    private PackageDao packageDao;

    private SqlSessionTemplate sqlSession;

    public PackageServiceImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    /*@Override
    public void CreatePackageInformation(PackageInfo p) {

    }

    @Override
    public void CreateOutPackageInformation(PackageInfo p) {

    }*/

    @Override
    public PackageInfo UpdatePackageInformation(String packageName) {
        packageDao = sqlSession.getMapper(PackageDao.class);
        return packageDao.UpdatePackageInformation(packageName);
    }

    @Override
    public PackageInfo UpdateOutPackageInformation(String packageName) {
        packageDao = sqlSession.getMapper(PackageDao.class);
        return packageDao.UpdatePackageInformation(packageName);
    }
}
