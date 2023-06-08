create table if not exists products (
      id  bigserial not null,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      description varchar(255) not null,
      name varchar(100) not null,
      product_price numeric(19, 2) not null,
      product_type varchar(255) not null,
      quantity integer not null ,
      minimal_quantity integer default 3,
      primary key (id)
);
