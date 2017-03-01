/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Customer;

/**
 *
 * @author Administrator
 */
public class CustomerMemoryService {
    private Customer[] customerList;
    private int count;

    public CustomerMemoryService() {
        customerList = new Customer[10];
        customerList[count] = new Customer("A123456789","123456","Ken");
        count++;
    }
    
    public boolean checkIdExit(String id){
        for (int i = 0; i < count ; i++){
            Customer cust = customerList[i];
            if(id.equals(cust.getId())){
                return true;
            }
        }
        return false;
    }
    public Customer login(String id, String pwd){
        for (int i = 0; i < count ; i++){
            Customer cust = customerList[i];
            if(id.equals(cust.getId())){
                if(pwd.equals(cust.getPassword())){
                    return cust;
                }
            }
        }
        return null;
    }
    public void modifyPWD(String id, String newPwd){
        for (int i = 0; i < count ; i++){
            Customer cust = customerList[i];
            if(id.equals(cust.getId())){
                cust.setPassword(newPwd);
                break;
            }
        }
    }
    public boolean register(Customer cust){
        if(count==customerList.length){
            return false;
        }
        customerList[count] = cust;
        count++;
        return true;
    }
}
