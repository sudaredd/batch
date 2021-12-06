use batch;

create table employee_new (
    id int primary key,
    name varchar(100),
    salary int
);

insert into employee_new values (1,'name01',1000);
insert into employee_new values (2,'name02',2000);
insert into employee_new values (3,'name03',3000);
insert into employee_new values (4,'name04',4000);
insert into employee_new values (5,'name05',5000);
insert into employee_new values (6,'name06',36000);

create table manager (
    id int primary key auto_increment,
    name varchar(100),
    salary int
);