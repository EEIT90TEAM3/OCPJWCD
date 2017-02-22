/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;
import com.verygoodbook.entity.*;
/**
 *
 * @author Administrator
 */
public class TestVariableScope {
    public static void main(String[] args) {
        int i = 100;
        System.out.println("i = " + i);
        
        Object o = new Object();
        System.out.println("o = " + o);
        
        Customer c = new Customer(
                "A123456789", "123456", "張三");
        System.out.println("c = " + c);
        
        VIP vip = new VIP("A223456781", "123456", "林梅莉", 15);
        System.out.println("vip = " + vip);
    }
}
