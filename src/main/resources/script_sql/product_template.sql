create table "product_template"(id int primary key not null,
                                name varchar(50) not null,
                                unit_price float not null
                               );

insert into product_template (id, name, unit_price) values (1,'essence',5900);
insert into product_template (id, name, unit_price) values (2,'gasoil',4900);
insert into product_template (id, name, unit_price) values (3,'petrole',2130);