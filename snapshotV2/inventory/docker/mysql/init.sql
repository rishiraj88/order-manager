CREATE DATABASE IF NOT EXISTS invdb;

create table IF NOT EXISTS inventory_items(
    id varchar(36) primary key,
    sku_code varchar(35) not null,
    quantity_in_stock int not null,
    constraint qty_check check(quantity_in_stock > 0)
);

insert into inventory_items() values("dde8b268-9e88-4e77-9ddc-c4fa40cdb517","DE342GES34233111",10);
insert into inventory_items() values("dde8b268-9e88-4e77-9ddc-c4fa40cdd517","DE342GES34233112",12);
insert into inventory_items() values("dde8b268-9e88-4e77-9ddf-c4fa40cdb517","DE342GES34233113",8);
insert into inventory_items() values("dde8b268-9e89-4e77-9ddc-c4fa40cdb517","DE342GES34233114",3);
