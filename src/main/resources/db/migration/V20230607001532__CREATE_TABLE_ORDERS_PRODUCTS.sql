create table orders_products (
     order_id int8 not null,
     product_id int8 not null
);
alter table orders_products
    add constraint fk_product_id
        foreign key (product_id)
            references products;

alter table orders_products
    add constraint fk_order_id
        foreign key (order_id)
            references snack_bar_orders;
