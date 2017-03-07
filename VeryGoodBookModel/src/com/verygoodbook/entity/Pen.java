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
public class Pen extends Product{
    private double point = 0.5;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }   

    @Override
    public String toString() {
        return super.toString() + "\npoint=" + point;
    }
    
}
