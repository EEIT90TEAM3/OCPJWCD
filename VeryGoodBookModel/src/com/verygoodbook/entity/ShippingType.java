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
public enum ShippingType {
    FACE("店面取貨"), HOME("送貨到府", 100);

    private final String description;
    private final double fee;

    private ShippingType(String description) {
        this(description, 0);
    }

    private ShippingType(String description, double fee) {
        this.description = description;
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return this.description 
                + (fee == 0 ? "" : "-" + (int) fee );
    }
}
