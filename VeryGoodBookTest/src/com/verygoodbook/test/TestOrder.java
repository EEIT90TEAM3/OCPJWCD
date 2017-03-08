/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Order;
import com.verygoodbook.entity.PaymentType;
import com.verygoodbook.entity.Product;
import com.verygoodbook.entity.ShippingType;
import com.verygoodbook.entity.ShoppingCart;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.CustomerService;
import com.verygoodbook.service.ProductService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestOrder {

    public static void main(String[] args) {
        try {
            ProductService service = new ProductService();

            ShoppingCart cart1 = new ShoppingCart();
            Product p2 = service.get(1);
            cart1.add(p2, 1);
            
            Product p = service.get(7);
            p.setColor("red");
            cart1.add(p, 1);
            cart1.add(p, 1);
            
            Product p1 = service.get(7);
            p1.setColor("blue");
            cart1.add(p1, 1);
            cart1.add(p1, 1);

            System.out.println("cart1 data: " + cart1);

            CustomerService custService = new CustomerService();
//            Customer customer = custService.login("A123456789", "123456");
//            System.out.println("customer = " + customer);
//            cart1.setMember(customer);
//            System.out.println("cart1: " + cart1);
            
            Customer vip = custService.login("A223456781", "123456");
            System.out.println("vip = " + vip);

            ShoppingCart cart2 = new ShoppingCart();
            cart2.add(p2, 1);
            cart2.add(p, 1);
            cart2.add(p, 1);            
            cart2.add(p1, 1);
            cart2.add(p1, 1);
            cart2.setMember(vip);
            System.out.println("cart2: " + cart2);
            
            Order order1 = new Order();
            order1.setCustomer(vip);
            order1.add(cart2);
            order1.setPaymentType(PaymentType.FACE);
            order1.setPaymentFee(PaymentType.FACE.getFee());
            order1.setShippingType(ShippingType.FACE);
            order1.setShippingFee(ShippingType.FACE.getFee());
            System.out.println(order1);
            
            

        } catch (VGBException ex) {
            Logger.getLogger(TestOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
