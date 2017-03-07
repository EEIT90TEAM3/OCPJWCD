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
public class VIP extends Customer {
    private int discount;
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public VIP() {
    }
    public VIP(String id, String password, String name) {
        super(id, password, name);
    }
    public VIP(String id, String password, String name, String email, char gender) {
        super(id, password, name, email, gender);
    }
    public VIP(String id, String password, String name, int discount) {
        this(id, password, name);
//        this.discount = discount;
        this.setDiscount(discount);
    }
}
