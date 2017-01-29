
create table users (
id_user number primary key not null ,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(30),
password varchar2(30)
);

create table children (
id_child number primary key not null,
first_name varchar2(30),
last_name varchar2(30),
login varchar2(30),
password varchar2(30),
id_schedule number
);

create table monitors (
id_monitor number primary key not null,
id_user number,
id_child number
);

create table schedules(
id_schedule number primary key not null,
name varchar2 (30),
id_user number
);

create table schedule_entries(
id_schedule_entry number primary key not null,
name varchar2 (30),
time_start varchar2 (30),
time_stop varchar2(30),
day_of_week varchar2(30),
id_schedule number,
id_area number
);

create table locations (
id_location number primary key not null,
longitude number(10,7),
latitude number(10,7),
day varchar2(30),
time varchar(30),
id_child number
);

create table areas (
id_area number primary key not null,
longitude number(10,7),
latitude number(10,7),
radius number(12,7)
);

create table messages(
id_message number primary key,
id_child number,
id_user number,
message varchar2(50),
read char(1),
time varchar2(30)
);
