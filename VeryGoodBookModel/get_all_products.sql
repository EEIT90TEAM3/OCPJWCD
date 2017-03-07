SELECT products.id, products.name, 
    unit_price,stock,photo_url,description,discount,
    publishers.name AS publisher_name, auther_name
 FROM products 
    LEFT JOIN book_detail ON products.id = book_detail.product_id
    LEFT JOIN publishers ON book_detail.publisher_id = publishers.id
 WHERE products.type='Book';

SELECT products.id, products.name,
unit_price,photo_url,description,discount,auther_name
FROM products
LEFT JOIN book_detail ON products.id = book_detail.product_id
WHERE type='Book';


