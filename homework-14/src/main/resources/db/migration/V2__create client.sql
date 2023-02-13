-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
insert into address (street, client_id) values ('Krasnaya', 1);
insert into phone (number, client_id) values ('89181111111', 1);
insert into phone (number, client_id) values ('89181111118', 1);
insert into client (name, address_id, phone_id) values ('Иванов', 1, 1);
insert into client (name, address_id, phone_id) values ('Петров', 1, 1);