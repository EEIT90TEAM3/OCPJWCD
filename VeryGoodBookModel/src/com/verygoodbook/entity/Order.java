package com.verygoodbook.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private int id;
    private Customer customer;
    private Date createdTime=new Date();

    private PaymentType paymentType;
    private double paymentFee;
    private String paymentNote;

    private ShippingType shippingType;
    private double shippingFee;
    private String shippingNote;

    private String receiverName;
    private String receiverEmail;
    private String receiverAddress;
    private String receiverPhone;

    private int status;

    private double totalAmount;
    private Set<OrderItem> orderItemSet=new HashSet<>();
    
    //orderItemSet: mutators
    public void add(OrderItem item){//from DAO
        if(item!=null){
            orderItemSet.add(item);
        }
    }
    
    public void add(ShoppingCart cart){//from Controller
        orderItemSet.clear();
        for(Product p:cart.getProductsSet()){
            OrderItem item = new OrderItem();
            item.setProduct(p);
            if(p.getColor()!=null && p.getColor().length()>0){
                item.setColor(p.getColor());
            }
            
            if(!(p instanceof Book) && this.customer instanceof VIP){
                item.setPrice(p.getUnitPrice() * (100-((VIP)customer).getDiscount()) / 100);
            }else{
                item.setPrice(p.getUnitPrice());
            }
            
            item.setQuantity(cart.getQuantity(p));
            
            orderItemSet.add(item);
        }
    }    
    
    //orderItemSet: accessers
    public Set<OrderItem> getOrderItemSet() {        
        return new HashSet(orderItemSet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getShippingNote() {
        return shippingNote;
    }

    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalAmount() {
        if(orderItemSet!=null && orderItemSet.size()>0){
            double amount = 0;
            for(OrderItem item:orderItemSet){
                amount += item.getPrice()*item.getQuantity();
            }
            return amount;
        }else{
            return totalAmount;
        }
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ",\n createdTime=" + createdTime + ", paymentType=" + paymentType + ", paymentFee=" + paymentFee + ", paymentNote=" + paymentNote + ", shippingType=" + shippingType + ", shippingFee=" + shippingFee + ", shippingNote=" + shippingNote + ", receiverName=" + receiverName + ", receiverEmail=" + receiverEmail + ", receiverAddress=" + receiverAddress + ", receiverPhone=" + receiverPhone + ", status=" + status + ",\n"
                + ", orderItemSet=" + orderItemSet
                + ", 總金額=" + this.getTotalAmount()  + '}';
    }

    
}
