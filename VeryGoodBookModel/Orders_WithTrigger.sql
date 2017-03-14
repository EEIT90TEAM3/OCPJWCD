USE vgb;

/* 確實已存在orders table, 且其中有status欄位  */
DROP TABLE IF EXISTS `vgb`.`order_status_log`;
CREATE TABLE  `vgb`.`order_status_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL,
  `old_status` int(10) unsigned NOT NULL,
  `new_status` int(10) unsigned NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TRIGGER log_orders_status AFTER UPDATE ON orders FOR EACH ROW
    INSERT INTO order_status_log(order_id, old_status, new_status) 
		VALUES (new.id, old.status, new.status);
