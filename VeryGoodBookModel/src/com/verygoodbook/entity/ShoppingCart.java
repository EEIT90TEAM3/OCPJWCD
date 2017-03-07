/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ShoppingCart {

    private Customer member;
    private Map<Product, Integer> cart = new HashMap<>();

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    //mutators
    public void add(Product p, int quantity) {
        Integer oldQuantity = cart.get(p);
        if (oldQuantity == null) {
            cart.put(p, quantity);
        } else {
            cart.put(p, quantity + oldQuantity);
        }
    }

    public void update(Product p, int quantity) {
        cart.put(p, quantity);
    }

    public void remove(Product p) {
        cart.remove(p);
    }

    //accessors...
    public Set<Product> getProductsSet() {
        return cart.keySet();
    }

    public Integer getQuantity(Product p) {
        return cart.get(p);
    }

    public int size() {
        return cart.size();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    //business method
    public int getTotalQuantity() {
        int sum = 0;
        for (Integer quantity : cart.values()) {
            if (quantity == null) {
                continue;
            }
            sum += quantity;
        }
        return sum;
    }

    public double getTotalAmount() {
        double amount = 0;
        for (Product p : cart.keySet()) {
            if (cart.get(p) == null) {
                continue;
            }
            amount += p.getUnitPrice() * cart.get(p);
        }
        return amount;
    }

    public double getVIPTotalAmount() {
        if(!(member instanceof VIP)){
            return getTotalAmount();
        }
        
        double amount = 0;
        for (Product p : cart.keySet()) {
            if (cart.get(p) == null) {
                continue;
            }
            if(!(p instanceof Book)){
                amount += p.getUnitPrice() * cart.get(p) * (100-((VIP)member).getDiscount())/100;
            }else{
                amount += p.getUnitPrice() * cart.get(p);
            }
        }
        return amount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "member=" + member + ", cart=" + cart + "}, " +
        "總數量: " + this.getTotalQuantity() + ", " +
        "未折扣總金額: " + this.getTotalAmount() + ", " + 
        "實際總金額: " + this.getVIPTotalAmount();
    }
    
    
}
