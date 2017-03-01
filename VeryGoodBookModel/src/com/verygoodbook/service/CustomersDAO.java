/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.VIP;
import com.verygoodbook.exception.VGBException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CustomersDAO 
        implements DAOInterface<String, Customer> {

//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/vgb?zeroDateTimeBehavior=convertToNull";
//    private static final String userid = "root";
//    private static final String pwd = "1234";
    
    
    private static final String INSERT_SQL = "INSERT INTO customers " +
            "(id,name,password,email,gender," +
            " phone,address,married,birthday,type,discount) " +
            " VALUES(?,?,?,?, ?,?,?,?, ?,?,?)";    
    @Override
    public void insert(Customer c) throws VGBException{
        if(c==null){
            throw new java.lang.IllegalArgumentException("新增客戶不得為null");
        }
        
        try(Connection connection = RDBConnection.getConnection(); //1.建立連線
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL); //2.準備指令
                ){
            //傳入sql指令的參數值
            pstmt.setString(1, c.getId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getPassword());
            pstmt.setString(4, c.getEmail());
            pstmt.setString(5, String.valueOf(c.getGender()));            
            pstmt.setString(6, c.getPhone());
            pstmt.setString(7, c.getAddress());
            pstmt.setBoolean(8, c.isMarried());
            if(c.getBirthday()!=null){
                pstmt.setDate(9, new java.sql.Date(c.getBirthday().getTime()));
            }else{
                pstmt.setDate(9,null);
            }           
            pstmt.setString(10, c.getClass().getSimpleName());
            if(c instanceof VIP){
                pstmt.setInt(11, ((VIP) c).getDiscount());
            }else{
                pstmt.setInt(11, 0);
            }           
            
            //3. 執行指令
            int row =pstmt.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶新增失敗", ex);
            throw new VGBException("客戶新增失敗!", ex);            
        }
    }
    
    
    private static final String UPDATE_SQL="UPDATE customers "
            + "SET name=?, password=?, email=?, gender=?,"
            + "phone=?, address=?,  married=?, birthday=?, type=?, discount=? "
            + "WHERE id=?";
    
    @Override
    public void update(Customer c)throws VGBException{
        if(c==null){
            throw new java.lang.IllegalArgumentException("修改客戶不得為null");
        }
        
        try(Connection connection = RDBConnection.getConnection(); //1. 建立連線
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL); //2. 準備指令
                ){
            //3. 傳入指令的參數            
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getPassword());
            pstmt.setString(3, c.getEmail());
            pstmt.setString(4, String.valueOf(c.getGender()));            
            pstmt.setString(5, c.getPhone());
            pstmt.setString(6, c.getAddress());
            pstmt.setBoolean(7, c.isMarried());
            if(c.getBirthday()!=null){
                pstmt.setDate(8, new java.sql.Date(c.getBirthday().getTime()));
            }else{
                pstmt.setDate(8,null);
            }           
            pstmt.setString(9, c.getClass().getSimpleName());
            if(c instanceof VIP){
                pstmt.setInt(10, ((VIP) c).getDiscount());
            }else{
                pstmt.setInt(10, 0);
            }          
            pstmt.setString(11, c.getId());
            
            //4. 執行指令
            pstmt.executeUpdate();            
        }catch(SQLException ex){
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶修改失敗", ex);
            throw new VGBException("客戶修改失敗!", ex);
        }
    }    
    
    private static final String DELETE_SQL="DELETE FROM customers WHERE id = ?";    
    @Override
    public void delete(Customer c)throws VGBException{
        if(c==null || c.getId()==null){
            throw new java.lang.IllegalArgumentException("刪除客戶主鍵值不得為null");
        }
        
        try(Connection connection = RDBConnection.getConnection(); //1. 建立連線
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL); //2. 準備指令
                ){
            //3. 傳入指令的參數            
            pstmt.setString(1, c.getId());
            
            //4. 執行指令
            pstmt.executeUpdate();            
        }catch(SQLException ex){
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "客戶刪除失敗", ex);
            throw new VGBException("客戶刪除失敗!", ex);
        }
    }

    @Override
    public Customer get(String id) throws VGBException {
        if(id==null){
            throw new java.lang.IllegalArgumentException("查詢客戶時帳號不得為null");
        }

        try ( //2. 建立連線Connection
                Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM customers WHERE id=?");) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery();) { //SQL INJECTION                
                Customer c = null;
                while (rs.next()) {
                    String type = rs.getString("type");
                    if ("VIP".equals(type)) {
                        c = new VIP();
                    } else {
                        c = new Customer();
                    }

                    String idData = rs.getString("id");
                    c.setId(idData);
                    c.setName(rs.getString("name"));
                    c.setPassword(rs.getString("password"));
                    c.setEmail(rs.getString("email"));
                    c.setGender(rs.getString("gender").charAt(0));
                    c.setPhone(rs.getString("phone"));
                    c.setAddress(rs.getString("address"));
                    c.setMarried(rs.getBoolean("married"));
                    c.setBirthday(rs.getDate("birthday"));
                    if (c instanceof VIP) {
                        ((VIP) c).setDiscount(rs.getInt("discount"));
                    }
                    System.out.println("c = " + c);
                }
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "無法查詢客戶", ex);
            throw new VGBException("無法查詢客戶!", ex);
        }

    }

//TODO:     
    private static final String SELECT_CUSTOMERS_BY_NAME = "SELECT id, name, password,email,gender,"
            + "phone,address, married,birthday, type, discount "
            + "FROM customers "
            + "WHERE name LIKE ?";

    public List<Customer> getCustomerListByName(String name) throws VGBException {
        List<Customer> list = new ArrayList<>();

        try (//2. 建立連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令
                PreparedStatement pstmt = connection.prepareStatement(SELECT_CUSTOMERS_BY_NAME);) {
            pstmt.setString(1, name + '%');
            try (ResultSet rs = pstmt.executeQuery()) { //4.執行指令
                //5. 處理resultset
                while (rs.next()) {
                    Customer c = null;
                    String type = rs.getString("type");
                    if ("VIP".equals(type)) {
                        c = new VIP();
                    } else {
                        c = new Customer();
                    }

                    c.setId(rs.getString("id"));
                    c.setName(rs.getString("name"));
                    c.setPassword(rs.getString("password"));
                    c.setGender(rs.getString("gender").charAt(0));
                    c.setEmail(rs.getString("email"));
                    c.setPhone(rs.getString("phone"));
                    c.setAddress(rs.getString("address"));
                    c.setBirthday(rs.getDate("birthday"));
                    c.setMarried(rs.getBoolean("married"));

                    if (c instanceof VIP) {
                        ((VIP) c).setDiscount(rs.getInt("discount"));
                    }

                    list.add(c);
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "無法用部分姓名查詢客戶", ex);
            throw new VGBException("無法用部分姓名查詢客戶", ex);
        }

    }
}
