/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Product;
import com.verygoodbook.exception.VGBException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ProductService {
    private ProductsDAO dao = new ProductsDAO();

    public Product get(Integer id) throws VGBException {
        return dao.get(id);
    }

    public List<Product> getProductsByType(String type) throws VGBException {
        return dao.getProductsByType(type);
    }
    
    //TODO: insert, update, delete Product

    public List<Product> getProductsByName(String name) throws VGBException {
        return dao.getProductsByName(name);
    }
}
