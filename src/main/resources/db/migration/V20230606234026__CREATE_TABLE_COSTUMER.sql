create table if not exists costumer (
  id  bigserial not null,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  birth_date timestamp,
  email varchar(100),
  name varchar(100),
  password varchar(100),
  fone_number int4,
  primary key (id)
);
