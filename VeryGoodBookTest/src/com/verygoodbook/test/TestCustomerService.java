/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.CustomerService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestCustomerService {
    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();
            Customer c = service.get("A123456789");            
            System.out.println("Answer=" + c);            
            
            Customer c2 = service.login("A123456789", " 1234  ");
            System.out.println("c2=" + c2);
        } catch (VGBException ex) {
            Logger.getLogger(TestCustomerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            System.out.println(ex);
        }  
    }
}
