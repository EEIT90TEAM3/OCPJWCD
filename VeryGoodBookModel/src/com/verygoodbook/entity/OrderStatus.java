/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.entity;

/**
 *
 * @author Administrator
 */
public enum OrderStatus {
    NEW("新訂單"), PAID("已付款"), CONFIRMED("已確認付款"), PROCESSED("調貨中"),
        SHIPPED("已出貨"),ARRIVED("已到貨"),CHECKED("已簽收"),COMPLETED("已結案"),
    CANCELED("已取消"),
        REJECTING("退貨中"), REJECTED("已退貨"),REFUNDING("退款中"),REFUNDED("已退款");
    
    private final String description;

    private OrderStatus(String description) {
        this.description = description;
    }

    
    
    public String getDescription() {
        return description;
    }
    
    
}
