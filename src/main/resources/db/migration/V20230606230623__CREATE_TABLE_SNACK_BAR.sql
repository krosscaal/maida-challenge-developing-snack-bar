create table if not exists snack_bar (
   id  bigserial not null,
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   email varchar(255) not null,
   name varchar(255) not null,
   fone_number int4,
   manager_id int8,
   primary key (id)
);
alter table snack_bar
    add constraint fk_snack_manager_id
        foreign key (manager_id)
            references manager
