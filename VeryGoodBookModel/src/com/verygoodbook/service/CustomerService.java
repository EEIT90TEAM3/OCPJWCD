/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CustomerService {
    private CustomersDAO dao = new CustomersDAO();
    //TODO: 會員註冊
    public void register(Customer c) throws VGBException{
        dao.insert(c);
    }
    
    //TODO: 會員登入
    public Customer login(String id, String pwd) throws VGBException{
        if(id==null || id.length()==0 || pwd==null ||pwd.length()==0  ){
            throw new IllegalArgumentException("登入失敗: 必須輸入帳號和密碼");
        }
        
        Customer c = dao.get(id);
        if(c!=null && pwd.equals(c.getPassword())){
            return c;
        }else{
            throw new VGBException("登入失敗!");
        }        
    }
    
    //TODO: 
    public void update(Customer c) throws VGBException {
        dao.update(c);
    }

    public void delete(Customer c) throws VGBException {
        dao.delete(c);
    }

    public List<Customer> getCustomerListByName(String name) throws VGBException {
        return dao.getCustomerListByName(name);
    }
    
    public Customer get(String id) throws VGBException{
        Customer c = dao.get(id);
        return c;
    }

    
}
