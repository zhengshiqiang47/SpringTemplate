package com.dyfwz.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by coder on 2017/7/4.
 */
public class MYDataSource extends BasicDataSource {

    public class XBasicDataSource extends BasicDataSource {
        @Override
        public synchronized void close() throws SQLException {
            DriverManager.deregisterDriver(DriverManager.getDriver("jdbc:mysql://rm-wz91qohzh442idseco.mysql.rds.aliyuncs.com:3306/zuhao666?useSSL=true"));
            super.close();
        }
    }

}
