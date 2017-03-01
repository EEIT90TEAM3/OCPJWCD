/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.test;

import com.verygoodbook.entity.Product;
import com.verygoodbook.exception.VGBException;
import com.verygoodbook.service.ProductsDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TestProductsDAO {
    public static void main(String[] args) {
        try {
            ProductsDAO dao = new ProductsDAO();
            Product p = dao.get(1);
            System.out.println("p = " + p);
            System.out.println(dao.getProdcutsByType("Book"));
        } catch (VGBException ex) {
            Logger.getLogger(TestProductsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
