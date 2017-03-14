/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Order;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.CustomerService;
import com.verygoodbook.service.OrderService;
import com.verygoodbook.utils.VeryGoodBookUitilities;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestOrderServiceUpdateNew2Paid {
    public static void main(String[] args) {
        try {
            CustomerService custService = new CustomerService();
            Customer c = custService.login("A123456789", "123456");
            
            OrderService service = new OrderService();
            Order order = service.get(c.getId(), 8);                       
            service.updateNew2Paid(order.getId(), c.getId(), 
                    "中國信託", "54321", "2017-03-14", "14:00", 
                     VeryGoodBookUitilities.webPriceFormat.format(order.getTotalAmountWithFee()));
        } catch (VGBException ex) {
            Logger.getLogger(TestOrderServiceUpdateNew2Paid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
