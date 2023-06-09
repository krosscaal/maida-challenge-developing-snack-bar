create table if not exists costumer (
  id  bigserial not null,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  birth_date timestamp,
  email varchar(100) not null,
  name varchar(100) not null,
  password varchar(100) not null,
  fone_number varchar(15),
  primary key (id)
);
