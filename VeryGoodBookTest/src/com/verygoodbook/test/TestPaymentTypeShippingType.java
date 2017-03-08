/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.PaymentType;
import com.verygoodbook.entity.ShippingType;

/**
 *
 * @author Administrator
 */
public class TestPaymentTypeShippingType {
    public static void main(String[] args) {
        
        System.out.println(PaymentType.FACE.toString());
        System.out.println(PaymentType.FACE.getFee());
        System.out.println(PaymentType.FACE.getDescription());
        System.out.println(PaymentType.FACE.ordinal());
        System.out.println(PaymentType.FACE.name());

        System.out.println(PaymentType.ATM);
        System.out.println(PaymentType.ATM.getFee());
        System.out.println(PaymentType.ATM.getDescription());
        System.out.println(PaymentType.ATM.ordinal());
        System.out.println(PaymentType.ATM.name());
        
        System.out.println(PaymentType.HOME);
        System.out.println(PaymentType.HOME.getFee());
        System.out.println(PaymentType.HOME.getDescription());
        System.out.println(PaymentType.HOME.ordinal());
        System.out.println(PaymentType.HOME.name());
        
        ShippingType[] shippings = ShippingType.values();
        for(ShippingType type:shippings){
            System.out.println("type.ordinal() = " + type.ordinal());
            System.out.println("type.name() = " + type.name());
            System.out.println("type.getFee() = " + type.getFee());
            System.out.println("type.getDescription() = " + type.getDescription());
            System.out.println("type = " + type);
        }
        
        //0 -> 0,FACE,"店面付款"
        PaymentType paymentType = PaymentType.values()[0];
        
        
        //FACE -> 0,FACE,"店面付款"
        PaymentType paymentType2 = PaymentType.valueOf("FACE"); 

        //店面付款 -> 0,FACE,"店面付款"        
        //PaymentType paymentType3 = PaymentType.valueOf("店面付款"); 

    }
}
