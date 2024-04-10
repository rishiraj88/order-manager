CREATE TABLE 'orders'
('id' bigint(35) NOT NULL AUTO_INCREMENT,
'order_number' varchar(255) DEFAULT NULL,
'itemSkuCode' varchar(255),
'pricePerItem' decimal(8,2),
'quantity' int(11),
PRIMARY KEY(id)
);


