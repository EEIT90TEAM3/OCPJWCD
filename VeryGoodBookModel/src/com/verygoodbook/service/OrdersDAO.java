/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Order;
import com.verygoodbook.entity.OrderItem;
import com.verygoodbook.entity.PaymentType;
import com.verygoodbook.entity.Product;
import com.verygoodbook.entity.ShippingType;
import com.verygoodbook.exception.VGBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class OrdersDAO implements DAOInterface<Integer, Order> {

//  public void updateNew2Paid(Order order){
//      //修改訂單，一般來說會先取消訂單再修改
//  }
//  public void updateNew2Shipped(Order order){
//      //修改訂單，貨到付款
//  }
//  public void updateNew2Checked(Order order){
//      //修改訂單，取貨付款
//  }
    private static final String INSERT_ORDER = "INSERT INTO orders "
            + "(id,customer_id,created_time,payment_type,payment_fee,shipping_type,shipping_fee,"
            + "receiver_name,receiver_email,receiver_address,receiver_phone) "
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id,product_id,color,price,quantity) "
            + " VALUES(?,?,?,?,?)";

    @Override
    public void insert(Order order) throws VGBException {
        if (order == null) {
            throw new java.lang.IllegalArgumentException("新增訂單時訂單物件不得為null");
        }
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstm1 = connection.prepareStatement(INSERT_ORDER,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pstm2 = connection.prepareStatement(INSERT_ORDER_ITEM);) {
            //?
            connection.setAutoCommit(false);
            //start transaction...
            try {
                //1.傳入INSERT_ORDER需要的資料
                pstm1.setInt(1, order.getId());
                pstm1.setString(2, order.getCustomer().getId());
                //先把sql的time轉成java的timestamp之後再轉成長整數
                pstm1.setTimestamp(3, new Timestamp(order.getCreatedTime().getTime()));
                //因為ShippingType是列舉型別所以setInt,ordianal回傳列舉型別!
                pstm1.setInt(4, order.getPaymentType().ordinal());
                pstm1.setDouble(5, order.getPaymentFee());
                pstm1.setInt(6, order.getShippingType().ordinal());
                pstm1.setDouble(7, order.getShippingFee());
                pstm1.setString(8, order.getReceiverName());
                pstm1.setString(9, order.getReceiverEmail());
                pstm1.setString(10, order.getReceiverAddress());
                pstm1.setString(11, order.getReceiverPhone());
                pstm1.executeUpdate();

                //2.取回order的id
                if (order.getId() < 1) {
                    //元件要關掉所以這裡才做錯誤處理
                    try (ResultSet rs = pstm1.getGeneratedKeys();) {
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            order.setId(id);
                        }
                    }
                }

                //3.INSERT_ORDER_ITEM
                for (OrderItem item : order.getOrderItemSet()) {
                    //3.1
                    pstm2.setInt(1, order.getId());

                    //先去OrderItem抓到宣告為product的getProduct方法，在product取的id
                    pstm2.setInt(2, item.getProduct().getId());
                    pstm2.setString(3, item.getColor());
                    pstm2.setDouble(4, item.getPrice());
                    pstm2.setInt(5, item.getQuantity());

                    pstm2.executeUpdate();
                }
                //?
                connection.commit();
            } catch (SQLException ex) {
                //?
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            System.out.println("Test:" + ex);
            throw new VGBException("建立訂單失敗");
        }
    }
    
    private static final String GET_ORDER_BY_ID
            = "SELECT orders.id,customer_id,created_time,"
            + "payment_type,payment_fee,payment_note,shipping_type,shipping_note,shipping_fee,"
            + "receiver_name,receiver_email,receiver_address,receiver_phone,"
            + "order_id,product_id,color"
            + ",price,quantity ,status,products.name,products.colors "
            + " FROM orders "
            + "JOIN order_items ON order_id=order_items.order_id "
            + "JOIN products ON order_items.product_id=products.id "
            + " WHERE orders.id=?";

    @Override
    public Order get(Integer id) throws VGBException {
         if (id == null) {
            throw new java.lang.IllegalArgumentException("新增訂單時訂單物件不得為null");
        }
        Order order = null;
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstm = connection.prepareStatement(GET_ORDER_BY_ID)) {
            pstm.setInt(1, id);
            try(ResultSet rs = pstm.executeQuery();){
                while(rs.next()){
                    if(order==null){
                        order=new Order();
                        order.setId(id);
                        order.setCreatedTime(rs.getTimestamp("created_time"));
                        order.setPaymentFee(rs.getDouble("payment_fee"));
                        order.setPaymentNote(rs.getString("payment_note"));
                        int pType =rs.getInt("payment_type");
                        if(pType>=0 && pType<PaymentType.values().length){
                            order.setPaymentType(PaymentType.values()[pType]);
                        }
                        
                        order.setShippingFee(rs.getDouble("shipping_fee"));
                        order.setShippingNote(rs.getString("shipping_note"));
                        int sType =rs.getInt("shipping_type");
                        if(sType>=0 && sType<ShippingType.values().length){
                            order.setShippingType(ShippingType.values()[sType]);
                        }
                        
                        order.setReceiverName(rs.getString("receiver_name"));
                        order.setReceiverEmail(rs.getString("receiver_email"));
                        order.setReceiverAddress(rs.getString("receiver_address"));
                        order.setReceiverPhone(rs.getString("receiver_phone"));
                        order.setStatus(rs.getInt("status"));
                    }
                    
                    OrderItem item=new OrderItem();
                    item.setOrderId(id);
                    item.setColor(rs.getString("color"));
                    item.setPrice(rs.getDouble("price"));
                    item.setQuantity(rs.getInt("quantity"));
                    
                    Product p=new Product();
                    p.setId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    String colors=rs.getString("colors");
                    if(colors!=null && colors.length()>0){
                        String[] colorNames=colors.split(",");
                        for(String colorName:colorNames){
                            p.addColor(colorName);
                        }
                    }
                    
                    item.setProduct(p);
                    order.add(item);
                }
            }
            return order;
        } catch (SQLException ex) {
            System.out.println("Test2:"+ex);
            throw new VGBException("建立訂單失敗");
        }

    }
    //TODO:查詢歷史訂單記錄
    private static final String GET_ORDERS_BY_CUSTOMER = 
            "SELECT orders.id, customer_id, SUM(price*quantity) AS total_amount,"
            + "created_time,payment_type,payment_fee," 
            + "shipping_type,shipping_fee,receiver_name,receiver_email," 
            + "receiver_address,receiver_phone " 
            + "FROM orders " 
            + "JOIN order_items ON orders.id=order_items.order_id " 
            + "WHERE customer_id=? " 
            + "AND created_time BETWEEN DATE_ADD(now(), INTERVAL -1 MONTH) AND now()" 
            +  "GROUP BY orders.id ORDER BY created_time DESC";
    public List<Order> getOrdersByCustomer(String customerId){
        List<Order> list =new ArrayList<>();
        return list;
    }
    
    
    /**
     *
     * @param c
     * @throws VGBException
     * @deprecated:不支援
     */
    @Deprecated
    @Override
    public void update(Order c) throws VGBException {
        throw new UnsupportedOperationException("不支援"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param c
     * @throws VGBException
     * @deprecated:不支援
     */
    @Deprecated
    @Override
    public void delete(Order c) throws VGBException {
        throw new UnsupportedOperationException("不支援"); //To change body of generated methods, choose Tools | Templates.
    }
}
