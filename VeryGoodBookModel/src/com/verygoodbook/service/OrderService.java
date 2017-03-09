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
    //代理人程式，讓user不能直接存取DAO
    private OrdersDAO dao =new OrdersDAO();

    public void insert(Order order) throws VGBException {
        dao.insert(order);
    }

    public Order get(Integer id) throws VGBException {
        return dao.get(id);
    }
    //客戶查自己訂單
    public Order get(String customerId,Integer id)throws VGBException{
        Order order=dao.get(id);
        if(customerId.equals(order.getCustomer().getId())){
            return order;
        }else{            
             throw new VGBException("非客戶本人不得查詢歷史訂單");
        }       
    }
    public List<Order> getOrdersByCustomer(String customerId) {
        return dao.getOrdersByCustomer(customerId);
    }

    
    
}
