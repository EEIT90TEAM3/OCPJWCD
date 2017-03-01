SELECT products.id, products.name,
    unit_price,stock,photo_url,description,
    discount,publishers.name AS publisher_name,auther_name,isbn,publish_date,type,
    products.type
 FROM products 
    LEFT JOIN book_detail ON products.id = book_detail.product_id
    LEFT JOIN publishers ON book_detail.publisher_id = publishers.id
WHERE type = 'Book';
