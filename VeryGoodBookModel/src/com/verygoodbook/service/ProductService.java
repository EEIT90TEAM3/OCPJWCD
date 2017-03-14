/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Product;
import com.verygoodbook.exception.VGBException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ProductService {
    private ProductsDAO dao = new ProductsDAO();

    public Product get(Integer id) throws VGBException {
        return dao.get(id);
    }

    public List<Product> getProductsByType(String... types) throws VGBException {
        Set<Product> productsSet = new HashSet<>();
        if(types!=null && types.length>0){
            for(String type:types){
                productsSet.addAll(dao.getProductsByType(type));
            }
            
            return new ArrayList<>(productsSet);
        }
        return null;
        
    }
    
    //TODO: insert, update, delete Product

    public List<Product> getProductsByName(String name) throws VGBException {
        return dao.getProductsByName(name);
    }
}
