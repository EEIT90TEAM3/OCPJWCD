/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.exception.VGBException;

/**
 *
 * @author Administrator
 */
public class TestCustomerBirthday {
    public static void main(String[] args) {
        Customer c = new Customer();
        try{
        c.setBirthday("1999/11/30");
            System.out.println("c.getBirthday() = " 
                    + c.getBirthday());
        }catch(VGBException ex){
            System.out.println("[client]"+ex);
        }
    }//IOException
}
