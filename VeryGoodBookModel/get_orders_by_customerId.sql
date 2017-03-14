SELECT orders.id, customer_id, SUM(price*quantity) AS total_amount, created_time,payment_type,payment_fee, 
shipping_type,shipping_fee,receiver_name,receiver_email,
receiver_address,receiver_phone
FROM orders 
JOIN order_items ON orders.id=order_items.order_id
WHERE customer_id='A223456781' 
  AND created_time BETWEEN DATE_ADD(now(), INTERVAL -1 MONTH) AND now()
GROUP BY orders.id
ORDER BY created_time DESC