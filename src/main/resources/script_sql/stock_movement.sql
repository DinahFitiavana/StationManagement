create table "stock_movement"
(
    id       int primary key not null,
    date     timestamp        not null,
    type     varchar(50)     not null,
    quantity decimal         not null
);

insert into stock_movement (id, "date", type, quantity) VALUES (1,'2024-04-01 08:00','supply',100);
insert into stock_movement (id, "date", type, quantity) VALUES (2,'2024-04-01 09:00','sale',10);
insert into stock_movement (id, "date", type, quantity) VALUES (3,'2024-04-01 09:45','sale',5);
insert into stock_movement (id, "date", type, quantity) VALUES (4,'2024-04-01 12:00','sale',2);
insert into stock_movement (id, "date", type, quantity) VALUES (5,'2024-04-01 16:00','sale',14);

select sum(quantity)  from stock_movement where type = 'supply';
select sum(quantity) from stock_movement where type = 'sale';