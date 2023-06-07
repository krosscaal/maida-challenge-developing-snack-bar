insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Coxinha de frango','COXINHA',10.0,'FOOD',15,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Coxinha de frango tamanho grande','BIG COXINHA',15.0,'FOOD',15,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Pão de Queijo tradicional','PÃO DE QUEIJO',7.0,'FOOD',20,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Pão de Queijo Tamanho Grande','PÃO DE QUEIJO GRANDE',10.0,'FOOD',20,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Café xicara 80ml','CAFÉ',10.0,'DRINK',25,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Suco de Laranja Natural copo 200ml','SUCO LARANJA',10.0,'DRINK',25,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Refrigerante Coca Cola mini 200ml','COCA COLA MINI',10.0,'DRINK',25,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Bolo de pote sabor chocolate tamanho 220ml ','BOLO DE POTE CHOCOLATE',18.0,'DESSERT',13,3)ON CONFLICT DO NOTHING ;

insert into products(created_at, description, name,product_price,product_type,quantity,minimal_quantity)
values (localtimestamp,'Bolo de pote sabor abacaxi tamanho 220ml ','BOLO DE POTE ABACAXI',18.0,'DESSERT',13,3)ON CONFLICT DO NOTHING ;