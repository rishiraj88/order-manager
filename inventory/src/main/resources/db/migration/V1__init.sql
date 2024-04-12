CREATE TABLE `inventory`
(`id` bigint(20) NOT NULL AUTO_INCREMENT,
`item_sku_code` varchar(255),
`quantity_in_stock` int(11) DEFAULT NULL,
PRIMARY KEY(`id`)
);
