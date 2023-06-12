create table if not exists order_items (
     id  bigserial not null,
     created_at timestamp,
     updated_at timestamp,
     preco_unitario numeric(19, 2) not null,
     quantidade int4 not null,
     pedido_id int8,
     produto_id int8,
     primary key (id)
);
alter table if exists order_items
    add constraint fk_order_items_snack_bar_orders
        foreign key (pedido_id)
            references snack_bar_orders;

alter table if exists order_items
    add constraint fk_order_items_products
        foreign key (produto_id)
            references products;
