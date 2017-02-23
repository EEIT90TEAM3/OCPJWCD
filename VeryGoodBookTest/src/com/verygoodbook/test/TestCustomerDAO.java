/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.CustomersDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestCustomerDAO {
    public static void main(String[] args) {        
//        try {
//            Customer c1 = (Customer)Class.forName("com.verygoodbook.entity.VIP").newInstance();            
//            System.out.println("c1.getClass().getName() = " + c1.getClass().getName());
//        } catch (Exception ex) {
//            Logger.getLogger(TestCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }         
        
        try {
            Customer test = new Customer("A123456770", "123456", "王武", "five.wang@gmail.com", Customer.MALE);            
            CustomersDAO dao = new CustomersDAO();
            dao.insert(test);
            
            Customer c = dao.get("A123456770");
            System.out.println("Hello, Answer = " + c);
            
            c.setPhone("2222-3333");
            dao.update(c);
            
            List<Customer> list = dao.getCustomerListByName("王");
            System.out.println("list:" + list);
            
            dao.delete(c);
            list = dao.getCustomerListByName("王");
            System.out.println("list:" + list);
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
