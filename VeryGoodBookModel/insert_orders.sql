SELECT orders.id, customer_id, created_time,payment_type,payment_fee, 
shipping_type,shipping_fee,receiver_name,receiver_email,
receiver_address,receiver_phone,order_id,product_id,color,price,quantity,
products.name, products.colors FROM orders 
JOIN order_items ON orders.id=order_items.order_id
JOIN products ON order_items.product_id = products.id
WHERE customer_id='A223456781' AND orders.id = 1