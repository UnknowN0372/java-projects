-- ssm
-- 创建 lk_ssm
DROP DATABASE IF EXISTS lk_ssm;
CREATE DATABASE lk_ssm CHARSET=utf8mb4;
USE lk_ssm;

CREATE TABLE furn (
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
	`furn_name` VARCHAR(64) NOT NULL DEFAULT 'default_name' COMMENT "家居名",
	`maker` VARCHAR(64) NOT NULL DEFAULT 'default_maker' COMMENT "制造商/品牌名",
	`price` DECIMAL(11,2) NOT NULL DEFAULT 0.0 COMMENT "价格",
	`sales` INT(11) NOT NULL DEFAULT 0 COMMENT "销量",
	`stock` INT(11) NOT NULL DEFAULT 0 COMMENT "库存",
	`is_show` TINYINT NOT NULL DEFAULT 0 COMMENT "0 未上架,1上架"
) CHARSET= utf8mb4;
-- 创建测试数据
INSERT INTO furn (`furn_name`,`maker`,`price`,`sales`,`stock`,`is_show`)
VALUES("太阳灯", "黑马家居", 100.0, 99, 50, 1),
("太阳能电风扇", "硅谷家居", 200.0, 66, 99, 1),
("超级节能灯", "千锋家居", 150.0, 30, 999, 1),
("壁灯", "硅谷家居", 50.0, 120, 70, 1),
("吊灯", "黑马家居", 150.0, 180, 100, 1);
-- 测试sql
SELECT * FROM `furn`;
UPDATE `furn` SET `is_show` = 0 WHERE id  = 10;

INSERT INTO furn 
(id, furn_name, maker, price, sales, stock, is_show)
 VALUES (
 NULL, '小米电脑桌', 
 '小米家居', 
 999, 
 1000, 
 20, 
 NULL);
