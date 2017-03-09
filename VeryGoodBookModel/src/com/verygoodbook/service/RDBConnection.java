/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.exception.VGBException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Administrator
 */
public class RDBConnection {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/vgb?characterEncoding=utf-8";
    private static final String userid = "root";
    private static final String pwd = "1234";
    public static final int DUPLICATE_ENTRY_ERROR_CODE = 1062;

    public static Connection getConnection() throws VGBException {
        Context ctx;
        DataSource ds = null;
        Connection connection = null;

        try {
            ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("沒有JNDI環境");
            }
            
            //[java:comp/env/]是取得Web Server自己的Connection Pool必須要的設定，[jdbc/xxx]才是名稱
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/vgb");             
            connection = ds.getConnection();
            System.out.println("已經從Connection Pool取得連線");
            return connection;
        } catch (Exception e) {
            try {
                //1. 載入Driver
                Class.forName(driver);
                try {
                    //2. 建立連線並回傳
                    connection = DriverManager.getConnection(url, userid, pwd);
                    System.out.println("已經建立JDBC連線-" + connection);
                    return connection;
                } catch (SQLException ex) {
                    Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法建立連線", ex);
                    throw new VGBException("無法建立連線", ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法載入" + driver, ex);
                throw new VGBException("無法載入" + driver, ex);
            }
        }
    }
}
