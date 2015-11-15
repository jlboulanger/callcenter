insert into client(lastname, firstname, type, dt_create) values ('test', 'first', 'PROSPECT', '2015-01-01');
insert into client(lastname, firstname, type, dt_create) values ('test', 'second', 'PROSPECT', '2015-01-01');

insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '221B backer street', '', 'LONDON', 'England', 'postal code', '555-89745', '2015-01-01');
insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '222B backer street', '', 'LONDON', 'England', 'postal code', '555-89999', '2015-01-01');
insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (1, '223B backer street', '', 'LONDON', 'England', 'postal code', '555-88888', '2015-01-01');

insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (2, '26 rue Saint honoré', 'apt 45', 'Paris', 'France', '750008', '0145020112', '2015-01-01');
insert into contact_info(client_id, address1, address2, city, country, code, phone, dt_create) 
values (2, '26 rue Saint honoré', 'apt 21', 'Paris', 'France', '750008', '0145020113', '2015-01-01');

insert into employee(lastname, firstname, type, dt_create) values ('test', 'first', 'SUPERVISOR', '2015-01-01');
insert into employee(lastname, firstname, supervisor, type, dt_create) values ('test', 'second', 1, 'CALL_CENTER', '2015-01-01');