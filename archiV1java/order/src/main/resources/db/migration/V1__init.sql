CREATE TABLE IF NOT EXISTS `orders`
(`id` varchar(40) NOT NULL,
`order_number` varchar(255) DEFAULT NULL,
`item_sku_code` varchar(255),
`price_per_item` decimal(8,2),
`quantity` int(11),
`created` timestamp,
`updated` timestamp,
PRIMARY KEY(`id`)
);
