/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class TestRandom {
    public static void main(String[] args) {
        int len=6;
        Random r = new Random();        
        String rand = "";
        for(int i=0;i<len;i++){
            int data = r.nextInt(35);
            char aChar = (data<10)? (char)(data+'0'):(char)(data+'A'-10);
            System.out.println("aChar = " + aChar);
            rand += aChar;
        }
        
        System.out.println("rand = " + rand);
    }
}
