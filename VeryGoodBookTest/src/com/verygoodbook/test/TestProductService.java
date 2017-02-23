/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

//import com.sun.istack.internal.logging.Logger;/*錯誤!*/
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestProductService {

    public static void main(String[] args) {
        ProductService service = new ProductService();
        try{
            System.out.println(service.get(1));
            System.out.println(service.getProdcutsByType("Book"));
            System.out.println(service.getProdcutsByType("Product"));
        } catch(VGBException ex){
            Logger.getLogger(TestProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
