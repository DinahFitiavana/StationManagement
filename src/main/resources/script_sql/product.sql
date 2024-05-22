create table "product"( id int primary key,
                        id_station int references station(id),
                       id_product int references product_template(id),
                       id_stock_movement int references stock_movement(id)
                      id_evaporation_rate int references evaporation_rate(id)
                      );

insert into product (id, id_station, id_product, id_stock_movement) values (1,1,1,1);
insert into product (id, id_station, id_product, id_stock_movement) values (2,1,1,2);
insert into product (id, id_station, id_product, id_stock_movement) values (3,1,1,3);
insert into product (id, id_station, id_product, id_stock_movement) values (4,1,1,4);


