INSERT INTO orders
(id,customer_id,created_time,payment_type,payment_fee,shipping_type,shipping_fee,
receiver_name,receiver_email,receiver_address,receiver_phone)
VALUES(?,?,?,?,?,?,?,?,?,?,?);

INSERT INTO order_items
(order_id,product_id,color,price,quantity)
VALUES(?,?,?,?,?)