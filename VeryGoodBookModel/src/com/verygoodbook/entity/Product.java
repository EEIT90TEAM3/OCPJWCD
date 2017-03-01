package com.verygoodbook.entity;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Product {
    /**
     * 產品編號，由DB新增時自動給號，pkey
     */
    private int id;    
    private String name;
    
    /**
     * 圖片網址
     */
    private String photoUrl; 
    
    private String description; 
    
    /**
     * 定價即售價
     */
    private double unitPrice;     
    
    private int stock; //庫存量

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    public double getUnitPrice(){
        return this.unitPrice;
    }
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getStock(){
        return this.stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String toString(){
        return this.getClass().getName()+ "- Id : "+id+"\n"+"Name : "+name+"\n"+
                "Stock : "+stock+"\n"+"Description : "+description+"\n"+
                "UnitPrice : "+unitPrice+"\n"+"PhotoURL : "+photoUrl+"\n";
    }

    
}
