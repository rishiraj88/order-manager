CREATE TABLE `orders`
(`id` bigint(20) NOT NULL AUTO_INCREMENT,
`order_number` varchar(255) DEFAULT NULL,
`item_sku_Code` varchar(255),
`price_per_item` decimal(8,2),
`quantity` int(11),
PRIMARY KEY(`id`)
);
