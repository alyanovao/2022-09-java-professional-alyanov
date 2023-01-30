-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
insert into address (id, street) values (1, 'Krasnaya');
insert into phone (id, number, client_id) values (1,'89181111111', 1);
insert into phone (id, number, client_id) values (2,'89181111118', 1);
insert into client (id, name, address_id, phone_id) values (1, 'Иванов', 1, 1);
insert into client (id, name, address_id, phone_id) values (2, 'Петров', 1, 1);