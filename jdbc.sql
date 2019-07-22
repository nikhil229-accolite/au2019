create database jdbc;
use jdbc;

drop table if exists emp;
create table emp
(
id int primary key,
e_name varchar(256),
age int
);
insert into emp values(1,"ajay",12);
insert into emp values(2,"ram",20);
insert into emp values(3,"joy",30);


