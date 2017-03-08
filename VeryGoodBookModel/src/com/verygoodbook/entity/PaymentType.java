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
public enum PaymentType {
    FACE("店面付款",ShippingType.FACE), 
    ATM("ATM轉帳", ShippingType.HOME), 
    HOME("貨到付款", 150, ShippingType.HOME);
    
    private final String description;
    private final double fee;
    private final ShippingType shippingType;
    
    private PaymentType(String description, ShippingType shippingType) {
        this(description,0, shippingType);        
    }
    
    private PaymentType(String description, double fee, ShippingType shippingType) {
        this.description = description;
        this.fee = fee;
        this.shippingType = shippingType;
    }
    
    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    
    @Override
    public String toString() {
        return this.description +(fee==0?"":"-"+(int)fee+'元');
    }
}