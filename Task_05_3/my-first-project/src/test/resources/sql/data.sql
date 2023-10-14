insert into product(product_name, CATEGORY_ID,expiration_date,description,price,state)
values ('New Product 1', 1,'2025-10-01','description 1',1000.00,'DRAFT');

insert into product(product_name, CATEGORY_ID,expiration_date,description,price,state)
values ('New Product 2', 2,'2025-10-02','description 2',2000.00,'DRAFT');

insert into product(product_name, CATEGORY_ID,expiration_date,description,price,state)
values ('New Product 3', 3,'2025-10-03','description 3',3000.00,'DRAFT');


insert into account(first_name, last_name,email,password,role)
values ('Anna', 'Bieliaieva','anna@gmail.com','Qwerty999!','USER');

insert into account(first_name, last_name,email,password,role)
values ('Amina', 'Rukina','amina@gmail.com','Qwerty557!','USER');

insert into account(first_name, last_name,email,password,role)
values ('Olha', 'Ivanova','olha@gmail.com','Qwerty99!','USER');

insert into event(title,date,venue_id)
values ('Party1','2024-01-01',null);

insert into event(title,date,venue_id)
values ('Party2','2024-02-02',null);

insert into event(title,date,venue_id)
values ('Party3','2024-03-03',null);


insert into user_event(account_id,event_id)
values ('2','1');
insert into user_event(account_id,event_id)
values ('3','1');