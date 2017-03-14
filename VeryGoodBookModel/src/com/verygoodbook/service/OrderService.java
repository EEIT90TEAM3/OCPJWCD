/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Order;
import com.verygoodbook.exception.VGBException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class OrderService {
    private OrdersDAO dao = new OrdersDAO();

    public void insert(Order order) throws VGBException {
        dao.insert(order);
        //mailService.sendOrderNotification(order);
    }

    public Order get(Integer id) throws VGBException {
        return dao.get(id);
    }
    
    public Order get(String customerId, Integer id) throws VGBException {
        Order order = dao.get(id);
        if(customerId.equals(order.getCustomer().getId())){
            return order;
        }else{
            throw new VGBException("非客戶本人不得查詢歷史訂單");
        }        
    }

    public List<Order> getOrdersByCustomer(String customerId) throws VGBException {
        return dao.getOrdersByCustomer(customerId);
    }

    public void updateNew2Paid(int orderId, String customerId, 
            String bank, String last5Code,
            String transferDate, String transferTime, String amount) throws VGBException {
        String note = bank +" 後5碼:" + last5Code + ", 轉帳日期時間: "+transferDate + " " + transferTime
                + ", 金額: " + amount;
        dao.updateNew2Paid(orderId, customerId, note);
    }
    
    
    
}
