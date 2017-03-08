package com.verygoodbook.entity;

import java.util.Objects;

public class OrderItem {
    private int orderId;    
    private Product product;
    
    private String color="";
    private double price;
    private int quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(color==null) color="";
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.orderId;
        hash = 59 * hash + (this.product!=null?this.getProduct().getId():0);
        hash = 59 * hash + Objects.hashCode(this.color);
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
        final OrderItem other = (OrderItem) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "OrderItem{" + "orderId=" + orderId 
                + ", 產品=" + product 
                + ", 顏色=" + color
                + ", 售價=" + price 
                + ", 購買數量=" + quantity + '}';
    }
    
    
}
