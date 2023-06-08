create table if not exists manager_snack_bar (
     id  bigserial not null,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,
     birth_date timestamp,
     email varchar(100) not null,
     name varchar(100) not null,
     password varchar(100) not null,
     fone_number int4,
     snack_bar_name varchar(255) not null,
     primary key (id)
);