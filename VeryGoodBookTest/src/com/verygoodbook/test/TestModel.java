/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Book;
import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Gender;
import com.verygoodbook.entity.Product;
import com.verygoodbook.entity.Publisher;
import com.verygoodbook.entity.VIP;
import com.verygoodbook.exception.VGBException;

/**
 *
 * @author Administrator
 */
public class TestModel {
    public static void main(String[] args) throws VGBException{
        Product p = new Product();
        System.out.println(p);
        Customer c = new Customer();
        c.setBirthday(1986, -1, 23);
        c.setBirthday(1986, 1, 236);
        c.setBirthday(2003, 1, 23);
        System.out.println(c.getBirthday());
        System.out.println(c.getAge());
//        c.setGender('\t');
//        System.out.println(c.getGender());
        c.setGender('F');
        System.out.println(c.getGender());
        char gen = 'G';
        //Caller Validation
        if (c.checkGender(gen)){
            c.setGender(gen);
        }else{
            //UI Message
            System.out.println("UI Message : 性別資料不正確....");
        }
       //try{
        System.out.println(c.checkId("A123456789"));
//        System.out.println(c.checkId("123456789"));
//        System.out.println(c.checkId("A12345678A"));
//        System.out.println(c.checkId("中123456789"));
        System.out.println(c.checkId("B120863158"));
        System.out.println(c.checkId("I123456781"));
//       }catch(VGBException ex){
//           System.out.println(ex);
//       }
        VIP vip = new VIP();
        vip.setId("B120863158");
        vip.setDiscount(30);//七折
        System.out.println(vip.getDiscount());
        
        Publisher pub = new Publisher();
        pub.setId(1);
        pub.setName("悅知文化");
        pub.setAddress("105台北市松山區復興北路99號12樓");
        pub.setPhone("02-2719-8811");
        Book b1 = new Book();
        b1.setId(1);
        b1.setIsbn("978-986-93371-5-1");
        b1.setName("我想吃掉你的胰臟");
        b1.setAutherName("住野夜");
        b1.setUnitPrice(360);
        b1.setDiscount(15);//85折
        b1.setPublisher(pub);
        System.out.println(b1.getName()+": NT$"+b1.getUnitPrice());
        System.out.println(b1.getPublisher().getId() + "," + b1.getPublisher().getName());
        
        VIP vip2 = new VIP("A123456789","111","Ken",50);
        System.out.println(vip2.getDiscount());

        Customer c1 = new Customer("A123456789","111","Ken");
        Customer c2 = new Customer("A123456789","121","Ken");
        System.out.println(c1==c2);
        System.out.println(c1.equals(c2));//with override equals method
        
        Product p1 = new Product();
        p1.setId(1);
        Product p2 = new Product();
        p2.setId(1);
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));//without override equals method
        System.out.println(Gender.MALE);
    }
}
