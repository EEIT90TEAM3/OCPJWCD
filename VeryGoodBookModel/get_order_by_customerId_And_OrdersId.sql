SELECT orders.id,customer_id,created_time,payment_type,payment_fee,shipping_type,shipping_fee,
receiver_name,receiver_email,receiver_address,receiver_phone,order_id,product_id,color,status
,price,quantity ,products.name,products.colors FROM orders
JOIN order_items ON order_id=order_items.order_id
JOIN products ON order_items.product_id=products.id
WHERE orders.id=10
