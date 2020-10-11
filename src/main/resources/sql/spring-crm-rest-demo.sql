create database spring_crm_rest_demo;
use spring_crm_rest_demo;

create table customer
(
    id         int(11) primary key auto_increment,
    first_name varchar(45),
    last_name  varchar(45),
    email      varchar(45)
);

insert into customer
values (1, 'David', 'Adams', 'david@email.com'),
       (2, 'John', 'Doe', 'john@email.com'),
       (3, 'Ajay', 'Rao', 'ajay@email.com'),
       (4, 'Mary', 'Public', 'mary@email.com'),
       (5, 'Maxwell', 'Dixon', 'max@email.com');