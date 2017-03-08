/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Product;
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
public class TestShoppingCart {

    public static void main(String[] args) {
        try {
            ProductService service = new ProductService();
            List<Product> list1 = service.getProductsByType("Book");
            System.out.println("list1:" + list1);

            List<Product> list2 = service.getProductsByType("Pen");
            System.out.println("list2:" + list2);
            list2.get(0).setColor("blue");
            
            Product pen = service.get(list2.get(0).getId());
            pen.setColor("red");
            System.out.println("pen:" + pen);

            ShoppingCart cart1 = new ShoppingCart();
            cart1.add(service.get(7), 1);
            cart1.add(service.get(7), 1);
            cart1.add(service.get(7), 1);
            cart1.add(service.get(1), 1);

            System.out.println("cart1 data: " + cart1);
//            System.out.println("cart1.getProductsSet:" + cart1.getProductsSet());
//            System.out.println("cart1.size() = " + cart1.size());
//            System.out.println("cart1.getQuantity(pen) = " + cart1.getQuantity(pen));
//            System.out.println("cart1.getVIPTotalAmount() = " + cart1.getVIPTotalAmount());

            CustomerService custService = new CustomerService();
            Customer customer = custService.login("A123456789", "123456");
            System.out.println("customer = " + customer);
            cart1.setMember(customer);
            System.out.println("cart1: " + cart1);
//            System.out.println("cart1.getProductsSet:" + cart1.getProductsSet());
//            System.out.println("cart1.size() = " + cart1.size());
//            System.out.println("cart1.getQuantity(pen) = " + cart1.getQuantity(pen));
//            System.out.println("cart1.getVIPTotalAmount() = " + cart1.getVIPTotalAmount());
            
            Customer vip = custService.login("A223456781", "123456");
            System.out.println("vip = " + vip);

            ShoppingCart cart2 = new ShoppingCart();
            cart2.add(pen, 1);
            cart2.add(list2.get(0), 2);
            cart2.add(list1.get(0), 1);
            cart2.setMember(vip);
            System.out.println("cart2: " + cart2);
//            System.out.println("cart2.getProductsSet:" + cart2.getProductsSet());
//            System.out.println("cart2.size() = " + cart2.size());
//            System.out.println("cart2.getQuantity(pen) = " + cart2.getQuantity(pen));
//            System.out.println("cart2.getVIPTotalAmount() = " + cart2.getVIPTotalAmount());

        } catch (VGBException ex) {
            Logger.getLogger(TestShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
