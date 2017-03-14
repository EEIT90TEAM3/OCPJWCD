/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.service;

import com.verygoodbook.entity.Customer;
import com.verygoodbook.entity.Order;
import com.verygoodbook.entity.OrderItem;
import com.verygoodbook.entity.OrderStatus;
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

    private static final String UPDATE_ORDER_STATUS_NEW2PAID = "UPDATE orders "
            + " SET payment_note=?, status=" + OrderStatus.PAID.ordinal()
            + " WHERE customer_id=? AND id=? "
            + " AND status=" + OrderStatus.NEW.ordinal()
            + " AND payment_type=" + PaymentType.ATM.ordinal();

    public void updateNew2Paid(int orderId, String customerId, String note) throws VGBException{
        if (orderId < 1 || customerId == null || note == null) {
            throw new java.lang.IllegalArgumentException("修改訂單狀態時"
                    + "訂單編號，客戶，付款備註 不得為null!");
        }

        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_ORDER_STATUS_NEW2PAID);) {
            pstmt.setString(1, note);
            pstmt.setString(2, customerId);
            pstmt.setInt(3, orderId);
            int rows = pstmt.executeUpdate();
            if(rows<1){
                throw new VGBException("訂單狀態不一致，無法修改! 請重新查詢!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new VGBException("修改訂單狀態(通知已付款)失敗", ex);
        }

    }

//    public void updateNew2Shipped(Order order){//
//        
//    }    
//    
//    public void updateNew2Checked(Order order){
//        
//    }    
    private static final String INSERT_ORDER = "INSERT INTO orders "
            + "(id,customer_id,created_time,payment_type,payment_fee,"
            + "shipping_type,shipping_fee,"
            + "receiver_name,receiver_email,receiver_address,receiver_phone) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id,product_id,color,price,quantity) "
            + "VALUES(?,?,?,?,?)";

    @Override
    public void insert(Order order) throws VGBException {
        if (order == null) {
            throw new java.lang.IllegalArgumentException("新增訂單時訂單物件不得為null");
        }

        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt1 = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDER_ITEM);) {
            connection.setAutoCommit(false);//begin transaction...
            try {
                //1.用 pstmt1 傳入 INSERT_ORDER 需要的資料...
                pstmt1.setInt(1, order.getId());
                pstmt1.setString(2, order.getCustomer().getId());
                pstmt1.setTimestamp(3, new Timestamp(order.getCreatedTime().getTime()));
                pstmt1.setInt(4, order.getPaymentType().ordinal());
                pstmt1.setDouble(5, order.getPaymentFee());
                pstmt1.setInt(6, order.getShippingType().ordinal());
                pstmt1.setDouble(7, order.getShippingFee());
                pstmt1.setString(8, order.getReceiverName());
                pstmt1.setString(9, order.getReceiverEmail());
                pstmt1.setString(10, order.getReceiverAddress());
                pstmt1.setString(11, order.getReceiverPhone());
                System.out.println(order);
                pstmt1.executeUpdate();

                System.out.println("order.getId() = " + order.getId());
                //2. 取回order的id...
                //int id=-1;
                if (order.getId() < 1) {
                    try (ResultSet rs = pstmt1.getGeneratedKeys();) {
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            order.setId(id);
                        }
                    }
                }
                //System.out.println("id = " + id);

                //3.INSERT_ORDER_ITEM
                for (OrderItem item : order.getOrderItemSet()) {
                    //3.1 用 pstmt2 傳入 INSERT_ORDER_ITEM 需要的資料...
                    pstmt2.setInt(1, order.getId());
                    item.setOrderId(order.getId());
                    pstmt2.setInt(2, item.getProduct().getId());
                    pstmt2.setString(3, item.getColor());
                    pstmt2.setDouble(4, item.getPrice());
                    pstmt2.setInt(5, item.getQuantity());
                    System.out.println(item);
                    pstmt2.executeUpdate();
                }
                connection.commit();//commit transaction
            } catch (SQLException ex) {
                connection.rollback();//rollback transaction
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new VGBException("建立訂單失敗", ex);
        }

    }

    private static final String GET_ORDER_BY_ID
            = "SELECT orders.id, customer_id, created_time,payment_type,payment_fee,payment_note,"
            + "shipping_type,shipping_fee,shipping_note,receiver_name,receiver_email,"
            + "receiver_address,receiver_phone,status,"
            + "product_id,color,price,quantity,"
            + "products.name, products.colors FROM orders "
            + "JOIN order_items ON orders.id=order_items.order_id "
            + "JOIN products ON order_items.product_id = products.id "
            + "WHERE orders.id=?";

    @Override
    public Order get(Integer id) throws VGBException {
        if (id == null) {
            throw new java.lang.IllegalArgumentException("查詢訂單時訂單編號不得為null");
        }

        Order order = null;
        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(GET_ORDER_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    if (order == null) {
                        order = new Order();

                        order.setId(id);
                        Customer c = new Customer();
                        c.setId(rs.getString("customer_id"));
                        order.setCustomer(c);

                        order.setCreatedTime(rs.getTimestamp("created_time"));
                        order.setPaymentFee(rs.getDouble("payment_fee"));
                        order.setPaymentNote(rs.getString("payment_note"));
                        int pType = rs.getInt("payment_type");
                        if (pType >= 0 && pType < PaymentType.values().length) {
                            order.setPaymentType(PaymentType.values()[pType]);
                        }

                        order.setShippingFee(rs.getDouble("shipping_fee"));
                        order.setShippingNote(rs.getString("shipping_note"));
                        int sType = rs.getInt("shipping_type");
                        if (sType >= 0 && sType < ShippingType.values().length) {
                            order.setShippingType(ShippingType.values()[sType]);
                        }

                        order.setReceiverName(rs.getString("receiver_name"));
                        order.setReceiverEmail(rs.getString("receiver_email"));
                        order.setReceiverAddress(rs.getString("receiver_address"));
                        order.setReceiverPhone(rs.getString("receiver_phone"));
                        order.setStatus(rs.getInt("status"));
                    }

                    OrderItem item = new OrderItem();
                    item.setOrderId(id);
                    item.setColor(rs.getString("color"));
                    item.setPrice(rs.getDouble("price"));
                    item.setQuantity(rs.getInt("quantity"));

                    Product p = new Product();
                    p.setId(rs.getInt("product_id"));
                    p.setName(rs.getString("name"));
                    String colors = rs.getString("colors");
                    if (colors != null && colors.length() > 0) {
                        String[] colorNames = colors.split(",");
                        for (String colorName : colorNames) {
                            p.addColor(colorName);
                        }
                    }
                    item.setProduct(p);
                    order.add(item);
                }
            }
            return order;
        } catch (SQLException ex) {
            throw new VGBException("查詢訂單失敗!", ex);
        }

    }

    private static final String GET_ORDERS_BY_CUSTOMER
            = "SELECT orders.id, SUM(price*quantity) AS total_amount,created_time,"
            + "payment_type,payment_fee,payment_note,"
            + "shipping_type,shipping_fee,shipping_note,"
            + "receiver_name,receiver_email,receiver_address,receiver_phone,status "
            + "FROM orders "
            + "JOIN order_items ON orders.id=order_items.order_id "
            + "WHERE customer_id=? "
            + "AND created_time BETWEEN DATE_ADD(now(), INTERVAL -1 MONTH) AND now()"
            + "GROUP BY orders.id ORDER BY created_time DESC";

    public List<Order> getOrdersByCustomer(String customerId) throws VGBException {
        if (customerId == null) {
            throw new java.lang.IllegalArgumentException("查詢客戶歷史訂單時客戶不得為null");
        }

        List<Order> list = new ArrayList<>();

        try (Connection connection = RDBConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(GET_ORDERS_BY_CUSTOMER)) {
            pstmt.setString(1, customerId);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));

                    Customer c = new Customer();
                    c.setId(customerId);
                    order.setCustomer(c);

                    order.setTotalAmount(rs.getDouble("total_amount"));
                    order.setCreatedTime(rs.getTimestamp("created_time"));
                    order.setPaymentFee(rs.getDouble("payment_fee"));
                    order.setPaymentNote(rs.getString("payment_note"));
                    int pType = rs.getInt("payment_type");
                    if (pType >= 0 && pType < PaymentType.values().length) {
                        order.setPaymentType(PaymentType.values()[pType]);
                    }

                    order.setShippingFee(rs.getDouble("shipping_fee"));
                    order.setShippingNote(rs.getString("shipping_note"));
                    int sType = rs.getInt("shipping_type");
                    if (sType >= 0 && sType < ShippingType.values().length) {
                        order.setShippingType(ShippingType.values()[sType]);
                    }

                    order.setReceiverName(rs.getString("receiver_name"));
                    order.setReceiverEmail(rs.getString("receiver_email"));
                    order.setReceiverAddress(rs.getString("receiver_address"));
                    order.setReceiverPhone(rs.getString("receiver_phone"));
                    order.setStatus(rs.getInt("status"));
                    list.add(order);
                }
            }
            return list;
        } catch (SQLException ex) {
            throw new VGBException("查詢客戶歷史訂單失敗!", ex);
        }
    }

    /**
     *
     * @param c
     * @throws VGBException
     * @deprecated:
     */
    @Deprecated
    @Override
    public void delete(Order c) throws VGBException {
        throw new UnsupportedOperationException("不支援"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param c
     * @throws VGBException
     * @deprecated:
     */
    @Deprecated
    @Override
    public void update(Order c) throws VGBException {
        throw new UnsupportedOperationException("不支援"); //To change body of generated methods, choose Tools | Templates.
    }

}
