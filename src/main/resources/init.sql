
create table users (
id int primary key not null ,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(40),
password varchar2(30)
);

create table children (
id int primary key not null,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(30),
password varchar2(30),
id_schedule int
);

create table monitors (
id int primary key not null,
id_user int,
id_child int
);

create table schedules(
id int primary key not null,
name varchar2 (30),
id_user int
);

create table schedule_entries(
id int primary key not null,
name varchar2 (30),
time_start varchar2 (30),
time_stop varchar2(30),
day varchar2(30),
id_schedule int,
id_area int
);

create table locations (
id int primary key not null,
longitude number(10,7),
latitude number(10,7),
day varchar2(30),
time varchar(30),
id_child int
);

create table areas (
id int primary key not null,
longitude number(10,7),
latitude number(10,7),
radius number(12,7)
);