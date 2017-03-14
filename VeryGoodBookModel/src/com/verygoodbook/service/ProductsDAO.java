/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Book;
import com.verygoodbook.entity.Pen;
import com.verygoodbook.entity.Product;
import com.verygoodbook.entity.Publisher;
import com.verygoodbook.exception.VGBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ProductsDAO implements DAOInterface<Integer, Product> {
    public void insert(Product p) throws VGBException {
        
    }

    public void update(Product p) throws VGBException {

    }

    public void delete(Product p) throws VGBException {
        
    }

    private static final String SELECT_PRODUCT_BY_ID = "SELECT products.id, products.name,"
            + "unit_price,stock,photo_url,description,"
            + "discount,publishers.name AS publisher_name,auther_name,isbn,publish_date,colors,"
            + "products.type "
            + "FROM products "
            + "LEFT JOIN book_detail ON products.id = book_detail.product_id "
            + "LEFT JOIN publishers ON book_detail.publisher_id = publishers.id "
            + "WHERE products.id = ?";

    public Product get(Integer id) throws VGBException {
        Product p = null;
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    String type = rs.getString("type");
                    if (type != null && type.equalsIgnoreCase("Book")) {
                        p = new Book();
                    }else if (type != null && type.equalsIgnoreCase("Pen")) {
                        p = new Pen();                        
                    } else {
                        p = new Product();
                    }
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unit_price"));
                    p.setStock(rs.getInt("stock"));
                    p.setPhotoUrl(rs.getString("photo_url"));
                    p.setDescription(rs.getString("description"));
                    if (p instanceof Book) {
                        ((Book) p).setDiscount(rs.getInt("discount"));
                        ((Book) p).setAutherName(rs.getString("auther_name"));
                        ((Book) p).setPublishDate(rs.getDate("publish_date"));
                        ((Book) p).setIsbn(rs.getString("isbn"));

                        Publisher publisher = new Publisher();
                        publisher.setName(rs.getString("publisher_name"));
                        ((Book) p).setPublisher(publisher);
                    }
                    String colors = rs.getString("colors");
                    if(colors!=null){
                        String[] colorNames = colors.split(",");
                        for(String name:colorNames){
                            p.addColor(name);
                        }
                    }
                }
                return p;
            }
        } catch (SQLException ex) {
            throw new VGBException("查詢產品失敗", ex);
        }
    }
    
    private static final String SELECT_PRODUCTS_BY_NAME = "SELECT "
            + "products.id, products.name,"
            + "unit_price,photo_url,description,discount,auther_name,type,colors "//,publishers.id as pub_id "
            + "FROM products "
            + "LEFT JOIN book_detail ON products.id = book_detail.product_id "
            //+ "LEFT JOIN publishers ON book_detail.publisher_id = publishers.id "
            + "WHERE products.name LIKE ?";   
    
    public List<Product> getProductsByName(String name) throws VGBException {
        List<Product> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt
                = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME);) {
            pstmt.setString(1, '%' + name + '%');
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Product p = null;
                    String rsType = rs.getString("type");
//                    if (rsType.equals("Book")) {
//                        p = new Book();
//                    } else {
//                        p = new Product();
//                    }

                    String className = Product.class.getName();
                    className = className.replaceAll(Product.class.getSimpleName(), rsType);                    
                    try{
                        p = (Product) Class.forName(className).newInstance();                    
                    }catch(Exception ex){
                        p = new Product();
                    }                   
                    
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unit_price"));
                    p.setPhotoUrl(rs.getString("photo_url"));
                    p.setDescription(rs.getString("description"));
                    String colors = rs.getString("colors");
                    if(colors!=null){
                        String[] colorNames = colors.split(",");
                        for(String colorName:colorNames){
                            p.addColor(colorName);
                        }
                    }

                    if (p instanceof Book) {
                        ((Book) p).setDiscount(rs.getInt("discount"));
                        ((Book) p).setAutherName(rs.getString("auther_name"));
                        
//                        Publisher publisher = new Publisher();
//                        publisher.setId(rs.getInt("pub_id"));
//                        ((Book) p).setPublisher(publisher);                                                
                    }
                    list.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new VGBException("依據名稱查詢產品失敗", ex);
        }
        return list;
    }

    private static final String SELECT_PRODUCTS_BY_TYPE = "SELECT "
            + "products.id, products.name,"
            + "unit_price,photo_url,description,discount,auther_name,type,colors " //,publishers.id as pub_id "
            + "FROM products "
            + "LEFT JOIN book_detail ON products.id = book_detail.product_id "
            //+ "LEFT JOIN publishers ON book_detail.publisher_id = publishers.id "
            + "WHERE type=?";

    public List<Product> getProductsByType(String type) throws VGBException {
        List<Product> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt
                = connection.prepareStatement(SELECT_PRODUCTS_BY_TYPE);) {
            pstmt.setString(1, type);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Product p = null;
                    String rsType = rs.getString("type");
//                    if (rsType.equals("Book")) {
//                        p = new Book();
//                    } else {
//                        p = new Product();
//                    }

                    String className = Product.class.getName();
                    className = className.replaceAll(Product.class.getSimpleName(), rsType);                    
                    try{
                        p = (Product) Class.forName(className).newInstance();                    
                    }catch(Exception ex){
                        p = new Product();
                    }                   
                    
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setUnitPrice(rs.getDouble("unit_price"));
                    p.setPhotoUrl(rs.getString("photo_url"));
                    p.setDescription(rs.getString("description"));
                    String colors = rs.getString("colors");
                    if(colors!=null){
                        String[] colorNames = colors.split(",");
                        for(String name:colorNames){
                            p.addColor(name);
                        }
                    }

                    if (p instanceof Book) {
                        ((Book) p).setDiscount(rs.getInt("discount"));
                        ((Book) p).setAutherName(rs.getString("auther_name"));
                        
//                        Publisher publisher = new Publisher();
//                        publisher.setId(rs.getInt("pub_id"));
//                        ((Book) p).setPublisher(publisher);                        
                    }
                    list.add(p);
                }
            }
        } catch (SQLException ex) {
            throw new VGBException("依據類型查詢產品失敗", ex);
        }
        return list;
    }

}
