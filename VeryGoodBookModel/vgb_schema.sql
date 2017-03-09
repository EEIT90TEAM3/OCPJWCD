DROP DATABASE IF EXISTS `vgb`;
CREATE DATABASE `vgb` DEFAULT CHARSET=utf8;  /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vgb`;

DROP TABLE IF EXISTS `vgb`.`customers`;
CREATE TABLE  `vgb`.`customers` (
  `id` char(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `gender` char(1) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `married` tinyint(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  `discount` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_Unique_Email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `vgb`.`customers`
(`id`, `name`, `password`,`email`,`gender`, `type`)
VALUES('A123456789', '張三豐', '123456', 'Sammul.chang@gmail.com', 'M', 'Customer');

INSERT INTO `vgb`.`customers`
(`id`, `name`, `password`,`email`,`gender`,
`phone`,`address`, `married`,`birthday`, `type`, `discount`)
VALUES('A223456781', '林梅莉', '123456', 'MarieLin@gmail.com', 'F', 
'02-25149191', '台北市復興北路99號14F',false, '1990-12-15', 'VIP', 15);

DROP TABLE IF EXISTS `vgb`.`publishers`;
CREATE TABLE  `vgb`.`publishers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO publishers
(id, name, address, phone) VALUES
(1,'博誌文化', '新北市汐止區新台五路一段106號B棟12樓', '(02)2696-2869'),
(2, 'PCuSER電腦人文化', null,null),
(3, '活泉', '新北市中和區中山路二段366巷10號10樓',null),
(4, '悅知文化','台北市松山區復興北路99號12樓','(02)2719-8811'),
(5, '大新書局','台灣台北市大安區瑞安街256巷16號','(02)2707-3232');

DROP TABLE IF EXISTS `vgb`.`products`;
CREATE TABLE  `vgb`.`products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `unit_price` double NOT NULL,
  `stock` int(10) unsigned NOT NULL DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `photo_url` varchar(120) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `discount` int(10) unsigned NOT NULL DEFAULT '0',
  `colors` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `vgb`.`book_detail`;
CREATE TABLE  `vgb`.`book_detail` (
  `product_id` int(10) unsigned NOT NULL,
  `subtitle` varchar(120) NOT NULL DEFAULT '',
  `auther_name` varchar(60) DEFAULT NULL,
  `isbn` char(13) DEFAULT NULL,
  `publish_date` date NOT NULL,
  `publisher_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK_book_detail_publishers` (`publisher_id`),
  CONSTRAINT `FK_book_detail_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_book_detail_publishers` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `vgb`.`products`
(id,name,unit_price,photo_url,stock,type,discount) VALUES
(1,'色鉛筆繪畫寶典：一次學會54種一般繪畫技法+15種水性色鉛筆繪畫技法',390,
    'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/069/41/0010694121.jpg&v=5624c669&w=348&h=348',
    10,'Book',50),
(2,'色鉛筆風景繪：28堂最細緻的光影練習，畫出魔幻時刻的空氣感手繪技法',350,
    'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/071/20/0010712097.jpg&v=5710decd&w=348&h=348',
    10,'Book',30),
(3,'最動人的色鉛筆彩繪：色調×意境的進階繪畫技法(暢銷珍藏版)',320,
    'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/067/14/0010671491.jpg&v=552f8fec&w=348&h=348',
    10,'Book',30),
(4,'狗狗繪：汪星人の色鉛筆手繪練習本',280,
    'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/072/62/0010726237.jpg&v=57b58e5e&w=348&h=348',
    10,'Book',30),
(5,'【德國LYRA】學用三角水彩色鉛筆，24色', 245,
 'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/N00/019/09/N000190939.jpg&v=4fc46dce&w=348&h=348',
 10,'Product',0),
(6,'施德樓ABS水彩色鉛筆組，36色', 560,
 'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/N00/016/82/N000168202.jpg&v=4fc450d3&w=348&h=348',
 10,'Product',0),
(7,'Monami-馬卡龍六角彩桿原子筆四入組-0.7mm-黑色筆芯', 150,
 'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/N00/073/21/N000732188.jpg&v=57d27c2d&w=348&h=348',
 10,'Pen',0),
(8, '滿點文法N4', 320,
 'http://im1.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/074/00/0010740048.jpg&v=5876095a&w=348&h=348',
  5,'Book',10);

INSERT INTO book_detail
(product_id, subtitle, auther_name,isbn,publish_date,publisher_id) VALUES
(1,'色鉛筆繪畫技法寶典', '蔥二', '9789869279949', '2015-10-30', 1), /*博誌*/
(2,'', '飛樂鳥工作室', '9789862101315', '2016-4-21', 2), /*PCuSER電腦人文化*/
(3,'', '飛樂鳥工作室', '4717702089610', '2015-4-18', 2), /*PCuSER電腦人文化*/
(4,'', '崔喬喬', '9789862717141', '2016-8-31', 3),
(8,'', ' 友松悅子,福島佐知,中村かおり', '9789863211181', '2016-11-1', 5);/*活泉*/

INSERT INTO `vgb`.`products`
(id,name,unit_price,photo_url,stock,type,discount) VALUES
(9, '零秒思考力【行動篇】：即斷、即決、即實行的瞬間執行力', 320,
 'http://im2.book.com.tw/image/getImage?i=http://www.books.com.tw/img/001/074/27/0010742733.jpg&v=5881925a&w=348&h=348',
  21,'Book',10);

INSERT INTO book_detail
(product_id, subtitle, auther_name,isbn,publish_date,publisher_id) VALUES
(9,'ゼロ秒思考[行動編]―即断即決、即実行のトレーニング', 
    '赤羽雄二', '9789863211181', '2017-2-6', 4);

DROP TABLE IF EXISTS `vgb`.`orders`;
CREATE TABLE  `vgb`.`orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` char(10) NOT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_type` int(10) unsigned NOT NULL,
  `payment_fee` double NOT NULL DEFAULT '0',
  `payment_note` varchar(100) DEFAULT NULL,
  `shipping_type` int(10) unsigned NOT NULL,
  `shipping_fee` double NOT NULL DEFAULT '0',
  `shipping_note` varchar(100) DEFAULT NULL,
  `receiver_name` varchar(20) NOT NULL,
  `receiver_email` varchar(40) NOT NULL,
  `receiver_address` varchar(100) NOT NULL,
  `receiver_phone` varchar(20) NOT NULL,
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_orders_customers` (`customer_id`),
  CONSTRAINT `FK_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `vgb`.`order_items`;
CREATE TABLE  `vgb`.`order_items` (
  `order_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `color` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`,`color`),
  KEY `FK_order_items_products` (`product_id`),
  CONSTRAINT `FK_order_items_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_order_items_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;