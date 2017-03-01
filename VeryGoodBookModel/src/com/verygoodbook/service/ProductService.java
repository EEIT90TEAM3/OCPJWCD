
package com.verygoodbook.service;

import com.verygoodbook.entity.Product;
import com.verygoodbook.exception.VGBException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ProductService {
    /*代理人程式，讓使用者不要直接存取DAO*/
    private ProductsDAO dao=new ProductsDAO();

    public Product get(Integer id) throws VGBException {
        return dao.get(id);
    }

    public List<Product> getProdcutsByType(String type) throws VGBException {
        return dao.getProdcutsByType(type);
    }
    //TODD:insert,update,delete,Product
    public List<Product> getProductsByName(String name) throws VGBException {
        return dao.getProdcutsByName(name);

    }
}
