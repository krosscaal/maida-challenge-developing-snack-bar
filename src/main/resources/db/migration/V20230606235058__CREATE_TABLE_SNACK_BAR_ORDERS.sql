create table if not exists snack_bar_orders (
   id  bigserial not null,
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   order_date timestamp not null,
   status varchar(255) not null,
   total_price numeric(19, 2) not null,
   costumer_id int8,
   primary key (id)
);
alter table snack_bar_orders
    add constraint fk_order_costumer_id
        foreign key (costumer_id)
            references costumer
