SELECT orders.id,customer_id,created_time,SUM(price*quantity) AS total_amount,
payment_type,payment_fee,shipping_type,shipping_fee,
receiver_name,receiver_email,receiver_address,receiver_phone,order_id,product_id,color,status
,price,quantity
FROM orders
JOIN order_items ON order_id=order_items.order_id
WHERE orders.id=10
AND customer_id='A223456781' BETWEEN DATE_ADD(now(), INTERVAL-1 MONTH) AND now()
GROUP BY orders.id
ORDER BY created_time DESC